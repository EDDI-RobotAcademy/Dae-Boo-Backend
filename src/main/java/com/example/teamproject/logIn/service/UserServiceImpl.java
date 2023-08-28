package com.example.teamproject.logIn.service;

import com.example.teamproject.logIn.dto.NaverOAuthToken;
import com.example.teamproject.logIn.entity.User;
import com.example.teamproject.logIn.repository.UserRepository;
import com.example.teamproject.logIn.service.UserService;
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
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final private PropertyUtil propertyUtil;
    final private RestTemplate restTemplate;
    final private UserRepository userRepository;

//    @Value("${client_id}")
//    private String CLIENT_ID1;
//
//    @Value("${client_secret}")
//    private String CLIENT_SECRET1;
//
//    @Value("${redirect_uri}")
//    private String REDIRECT_URI1;

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
    public NaverOAuthToken generateAccessToken(String code){
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
        //body.add("redirect_uri", REDIRECT_URI);
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

        User user = getNaverUserInfo(accessToken, headers);

        Optional<User> maybeUser = userRepository.findById(user.getId());
        if(maybeUser.isPresent()) {
            System.out.println("maybeUser is present");
            return response.getBody();

        } else {
            if(user != null) {
                userRepository.save(user);
            } else {
                System.out.println("user is null");
            }
        }


        return response.getBody();
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
            String profile_image = jsonNode.get("response").get("profile_image").asText();
            String age = jsonNode.get("response").get("age").asText().substring(0,2);
            String gender = jsonNode.get("response").get("gender").asText();
            String mobile = jsonNode.get("response").get("mobile").asText();
            String name = jsonNode.get("response").get("name").asText();
            String id = jsonNode.get("response").get("id").asText();

            User user = new User(nickname, profile_image, age, gender, mobile, name, id);
            return user;
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }


}
