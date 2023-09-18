package com.example.teamproject.user.service;

import com.example.teamproject.user.controller.form.LoginUserEmailForm;
import com.example.teamproject.user.dto.KakaoOAuthToken;
import com.example.teamproject.user.dto.NaverOAuthToken;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.redis.RedisService;
import com.example.teamproject.user.repository.UserRepository;
import com.example.teamproject.utility.PropertyUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService {

    final private PropertyUtil propertyUtil;
    final private UserRepository userRepository;
    final private RedisService redisService;


    //---------------------------------------------------NAVER----------------------------------------------------
    @Override
    public String getAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("naver_client_id");
        final String REDIRECT_URI = propertyUtil.getProperty("naver_redirect_uri");

        final String URL
                = "https://nid.naver.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" +
                CLIENT_ID +
                "&redirect_uri=" +
                REDIRECT_URI +
                "&state=1234";
        log.info(URL);
        return URL;
    }

    @Override
    public String generateAccessToken(String code){
        final String CLIENT_ID = propertyUtil.getProperty("naver_client_id");
        final String CLIENT_SECRET = propertyUtil.getProperty("naver_client_secret");

        // HTTP Header 생성
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
//        body.add("redirect_uri", REDIRECT_URI);
        body.add("code", code);
        body.add("client_secret", CLIENT_SECRET);
        body.add("state", "1234");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);
        ResponseEntity<NaverOAuthToken> response
                = restTemplate.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST, tokenRequest, NaverOAuthToken.class);

        System.out.println("response : " + response);
        //System.out.println(response);
        String accessToken = response.getBody().getAccess_token();
        System.out.println("accessToken : " + accessToken);

        // accessToken으로 가져온 user정보로 user 생성
        User user = getNaverUserInfo(accessToken, headers);

        // email로 존재하는 user인지 확인
        Optional<User> maybeUser = userRepository.findByEmail(user.getEmail());
        if(maybeUser.isPresent()) {
            System.out.println("maybeUser is present");
//            return response.getBody();

        } else {
            // 없는 user면 db에 저장
            if(user != null) {
                userRepository.save(user);
            } else {
                System.out.println("user is null");
            }
        }

        //할당된 userId 찾아서 반환
        Optional<User> maybeUserAfterSave = userRepository.findByEmail(user.getEmail());
        if(maybeUserAfterSave.isPresent()) {
            User user1 = maybeUserAfterSave.get();
            Long userId = user1.getUserId();
            LoginUserEmailForm loginUserEmailForm = new LoginUserEmailForm(response.getBody(), userId);

            // redis 에 사용자 임의 토큰 만들어서 저장해줌
            // 지속적인 저장이 될 필요가 없음, 로그인 시에만 있으면 되니까 !
            String userToken = UUID.randomUUID().toString();
            log.info("accountId: " + userId + ", userToken: " + userToken);

            redisService.setKeyAndValue(userToken, userId);
            return userToken;
        }
        return null;
    }

    //사용자 정보가져오기
    @Override
    public User getNaverUserInfo(String accessToken, HttpHeaders headers) {

        System.out.println("사용자 정보가져오기");
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> UserInfoRequest = new HttpEntity(headers);
        RestTemplate userInfoRestTemplate = new RestTemplate();
        ResponseEntity<String> userInfoResponse = userInfoRestTemplate.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST, UserInfoRequest, String.class);
        System.out.println("user info response = " + userInfoResponse);

        // HTTP 응답 받아오기
        String userInfoResponseBody = userInfoResponse.getBody();
        System.out.println("userInfoResponseBody = " + userInfoResponseBody);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(userInfoResponseBody);
            String nickname = jsonNode.get("response").get("nickname").asText();
//            String email = jsonNode.get("response").get("email").asText();
//            String profile_image = jsonNode.get("response").get("profile_image").asText();
            String age = jsonNode.get("response").get("age").asText().substring(0,2);
            String gender = jsonNode.get("response").get("gender").asText();
            String id = jsonNode.get("response").get("id").asText();

            User user = new User(nickname, id, gender, age);
            return user;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    //---------------------------------------------------KAKAO----------------------------------------------------
    @Override
    public String getKakaoAuthorizeCode() {
        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String REDIRECT_URI = propertyUtil.getProperty("redirect_uri");
        final String URL = "https://kauth.kakao.com/oauth/authorize";
        return URL + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    @Override
    public String kakaoCallback(String code){
        KakaoOAuthToken kakaoOAuthToken = getAccessToken(code);
        ResponseEntity<String> response = requestUserInfo(kakaoOAuthToken);
        User user = saveUserInfo(response);

        // redis 에 사용자 임의 토큰 만들어서 저장해줌
        // 지속적인 저장이 될 필요가 없음, 로그인 시에만 있으면 되니까 !
        String userToken = UUID.randomUUID().toString();
        log.info("accountId: " + user.getUserId() + ", userToken: " + userToken);

        redisService.setKeyAndValue(userToken, user.getUserId());
        return userToken;
    }

    private User saveUserInfo(ResponseEntity<String> response) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap;
        try{
            jsonMap = objectMapper.readValue(response.getBody(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON string", e);
        }

        // "properties" 키 아래의 중첩된 JSON 객체 파싱
        Map<String, Object> propertiesMap = (Map<String, Object>) jsonMap.get("properties");
        String nickname = (String) propertiesMap.get("nickname");
//        String profile_image = (String) propertiesMap.get("profile_image");

        // "kakao_account" 키 아래의 중첩된 JSON 객체 파싱
        Map<String, Object> kakaoAccountMap = (Map<String, Object>) jsonMap.get("kakao_account");
        String email = (String) kakaoAccountMap.get("email");
        String gender = (String) kakaoAccountMap.get("gender");
//        String age_range = (String) kakaoAccountMap.get("age_range");

        // age_range 값을 가져오고 "~" 이전의 부분을 추출
        String age_range = (String) kakaoAccountMap.get("age_range");
        if (age_range != null) {
            int tildeIndex = age_range.indexOf("~"); // "~"의 위치를 찾습니다.
            if (tildeIndex != -1) {
                age_range = age_range.substring(0, tildeIndex).trim(); // "~" 이전의 부분을 추출하고 공백을 제거합니다.
            }
        }

        Optional<User> maybeUser = userRepository.findByEmail(email);
        User savedUser;

        if(maybeUser.isEmpty()) {
            String name = nickname;
            savedUser = userRepository.save(new User(nickname, email, gender, age_range));
        } else {
            savedUser = maybeUser.get();
        }
        return savedUser;
    }

    private ResponseEntity<String> requestUserInfo(KakaoOAuthToken kakaoOAuthToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoOAuthToken.getAccess_token());

        final String USER_INFO_REQUEST = "https://kapi.kakao.com/v2/user/me";
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                USER_INFO_REQUEST,
                HttpMethod.GET,
                request,
                String.class
        );
        System.out.println("response.getBody() = " + response.getBody());
        return response;
    }

    private KakaoOAuthToken getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-type", "application/x-www-form-urlencoded");

        final String CLIENT_ID = propertyUtil.getProperty("client_id");
        final String CLIENT_SECRET = propertyUtil.getProperty("client_secrets");
        final String REDIRECT_URI = propertyUtil.getProperty("redirect_uri");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("code", code);
        body.add("client_secret",CLIENT_SECRET);

        final String CLIENT_TOKEN_REQUEST = "https://kauth.kakao.com/oauth/token";
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, headers);
        ResponseEntity<KakaoOAuthToken> response = restTemplate.exchange(
                CLIENT_TOKEN_REQUEST,
                HttpMethod.POST,
                tokenRequest,
                KakaoOAuthToken.class
        );

        System.out.println(response);
        System.out.println(response.getBody().getAccess_token());
        return response.getBody();
    }
}
