package com.example.teamproject.user.service;

import com.example.teamproject.user.dto.KakaoOAuthToken;
import com.example.teamproject.user.dto.NaverOAuthToken;
import com.example.teamproject.user.entity.User;
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
import java.util.List;
import java.util.Map;
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


    //-------------------------NAVER--------------------------


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
        String profile_image = (String) propertiesMap.get("profile_image");

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
//            savedMember = signUpRepository.save(new Member(name, nickname, email));
            savedUser = userRepository.save(new User(nickname, email, profile_image, gender, age_range));
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

    //-------------------------MY PAGE--------------------------

    //useid로 User를 찾음
    @Override
    public User findUserByUserId(Long userId) {
        User LoginUser = null;
        Optional<User> maybeUser = userRepository.findByUserId(userId);

        if (maybeUser.isPresent()) {
            LoginUser = maybeUser.get();
            System.out.println("LoginUser : " + LoginUser);
        } else {
            System.out.println("LoginUser를 찾지 못했습니다.");
        }

        return LoginUser;
    }

    // 회원 정보 조회
    @Override
    public User getUserInfo(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다!"));
    }

    @Override
    public List<User> userList() {
        List<User> userList = userRepository.findByActivateTrue();
        return userList;
    }

    @Override
    public Boolean stopUser(Long id) {
        Optional<User> maybeUser = userRepository.findById(id);
        if (maybeUser.isPresent()){
            User targetUser = maybeUser.get();
            targetUser.setActivate(false);
            userRepository.save(targetUser);

            return true;
        }
        return false;
    }
}
