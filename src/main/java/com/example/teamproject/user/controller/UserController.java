package com.example.teamproject.user.controller;

import com.example.teamproject.user.dto.*;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.redis.RedisService;
import com.example.teamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    final private UserService userService;
    final private RedisService redisService;

    // redis 에 저장된 userToken 으로 사용자 정보 조회하여 전달 (로그인 시 구동)
    @PostMapping("/testToken")
    public AccountResponse afterLogin (@RequestBody AfterLoginRequest requestForm) {
        log.info("afterLogin(): " + requestForm);

        Long accountId = redisService.getValueByKey(requestForm.getUserToken());
        log.info("accountId: " + accountId);

        return userService.findAccountInfoById(accountId);
    }

    // redis 에 저장된 userToken 삭제 (로그아웃)
    @PostMapping("/logout/userToken")
    public boolean redisLogout (@RequestBody AfterLoginRequest requestForm) {
        log.info("redisLogout(): "+ requestForm);
        return redisService.deleteByKey(requestForm.getUserToken());
    }

    // 내 정보 조회 API
    @GetMapping("/userInfo")
    public User requestUserInfo (@RequestParam("userId") Long userId) {
        log.info("requestUserInfo()");
        return userService.getUserInfo(userId);
    }

    @PostMapping("/manage/list")
    public List<User> userList() {
        List<User> userList = userService.userList();
        return userList;
    }

    @GetMapping("/manage/userStop")
    public User userStop (@RequestParam Long userId){
        return userService.stopUser(userId);
    }

    // 내 정보 수정 API
    @PutMapping("/user-info/{userId}")
    public UserInfoResponse userInfoModify(
            @PathVariable("userId") long userId,
            @RequestBody UserInfoModifyRequest request){
        log.info("UserInfoResponse() // request : " + request);
        return userService.modify(userId, request);
    }

    // 내 정보 탈퇴 API
    @DeleteMapping("/user-info")
    public void userInfoDelete (@RequestParam Long userId) {
        userService.delete(userId);
    }

    @GetMapping("/manage/userInfo")
    public User getAccountInfo (@RequestParam Long userId) {
        log.info("requestUserInfo()");
        return userService.getUserInfo(userId);
    }

    // 사용자 닉네임 중복확인
    @PostMapping("/duplication-nickname")
    public boolean nicknameDuplicationCheck (@RequestBody NicknameDuplicationRequest request) {
        log.info("nicknameDuplicationCheck()");
        return userService.nicknameDuplication(request);
    }
}
