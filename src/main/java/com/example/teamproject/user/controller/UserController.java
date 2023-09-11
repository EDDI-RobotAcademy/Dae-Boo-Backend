package com.example.teamproject.user.controller;

import com.example.teamproject.user.dto.UserInfoModifyRequest;
import com.example.teamproject.user.dto.UserInfoResponse;
import com.example.teamproject.user.entity.User;
import com.example.teamproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    final private UserService userService;

    // 내 정보 조회 API
    @GetMapping("/userInfo")
    public User requestUserInfo(@RequestParam Long userId) {
        log.info("requestUserInfo()");
        return userService.getUserInfo(userId);
    }

    @PostMapping("manage/list")
    public List<User> userList() {

        List<User> userList = userService.userList();
        return userList;
    }

    @GetMapping("/manage/userStop")
    public User userStop (@RequestParam Long userId){
        return userService.stopUser(userId);
    }

    // 내 정보 수정 API
    @PutMapping("/user-info")
    public UserInfoResponse userInfoModify(
            @RequestParam Long userId,
            @RequestBody UserInfoModifyRequest request
    ) {
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
}
