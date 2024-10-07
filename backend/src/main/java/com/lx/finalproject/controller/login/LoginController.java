package com.lx.finalproject.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lx.finalproject.service.createaccount.UserService;
import com.lx.finalproject.vo.UserVo;

@RestController
@RequestMapping("/api/users")
public class LoginController {  // 클래스명 변경: Login -> LoginController로 명명하는 것이 더 적합

    @Autowired
    private UserService userService;

    // 회원가입 처리 (POST 요청)
    @PostMapping("/register")
    public String createUser(@RequestBody UserVo user) {
        try {
            userService.createUser(user);
            return "User registered successfully!";
        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    // 사용자 조회 (GET 요청)
    @GetMapping("/{id}")
    public UserVo getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    // 로그인 처리 (POST 요청)
    @PostMapping("/login")
    public String login(@RequestBody UserVo user) {
        boolean isAuthenticated = userService.authenticate(user.getUserId(), user.getUserPw());
        if (isAuthenticated) {
            return "Login successful!";
        } else {
            return "Invalid userId or password.";
        }
    }
}
