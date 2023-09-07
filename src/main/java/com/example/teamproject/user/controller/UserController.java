package com.example.teamproject.user.controller;

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

    @GetMapping("/userInfo")
    public User requestUserInfo (Long userId) {
        log.info("requestUserInfo()");
        return userService.getUserInfo(userId);
    }
    @PostMapping("manage/list")
    public List<User> userList() {

        List<User> userList = userService.userList();
        return userList;
    }
    @PostMapping("/manage/userStop")
    public Boolean userStop (@RequestBody Long id){
        return userService.stopUser(id);
    }
}
