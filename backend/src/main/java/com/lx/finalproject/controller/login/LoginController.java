package com.lx.finalproject.controller.login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // 올바른 Path 임포트
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lx.finalproject.service.createaccount.UserService;
import com.lx.finalproject.vo.UserVo;

@RestController
@RequestMapping("/api/users")
public class LoginController {

    @Autowired
    private UserService userService;

    // 회원가입 처리 (POST 요청)
    @PostMapping("/create_account")
    public String createUser(@ModelAttribute UserVo user, @RequestParam("userImg") MultipartFile userImg) {
        try {
            // 이미지 저장 로직
            if (!userImg.isEmpty()) {
                // 고유한 파일명을 생성하여 저장
                String uniqueFileName = UUID.randomUUID().toString() + "_" + userImg.getOriginalFilename();
                Path uploadPath = Paths.get("uploads/"); // 파일이 저장될 경로
                Files.createDirectories(uploadPath); // 디렉토리 생성
                Path filePath = uploadPath.resolve(uniqueFileName);
                userImg.transferTo(filePath.toFile()); // 파일 저장

                // 파일 경로를 UserVo에 설정
                user.setUserImg(filePath.toString()); // 파일 경로를 설정
            }

            // 사용자 정보 저장
            userService.createUser(user);
            return "User registered successfully!";
        } catch (IOException e) {
            return "Registration failed: Could not save image - " + e.getMessage();
        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }
    }

    // 로그인 처리 (POST 요청)
    @PostMapping("/login")
    public int login(@RequestBody UserVo user) {
        boolean isAuthenticated = userService.authenticate(user.getUserId(), user.getUserPw());
        if (isAuthenticated) {
            return userService.getUserByUserId(user.getUserId());
        } else {
            return 700;
        }
    }
}
