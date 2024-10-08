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
    public String createUser(
        @RequestParam("userId") String userId,
        @RequestParam("userPw") String userPw,
        @RequestParam("userName") String userName,
        @RequestParam("userSsn") String userSsn,
        @RequestParam("userMobile") String userMobile,
        @RequestParam("userEmail") String userEmail,
        @RequestParam("userPerm") Integer userPerm,
        @RequestParam("regionPk") Integer regionPk,
        @RequestParam("regionPk2") Integer regionPk2,
        @RequestParam("userImg") MultipartFile userImg
    ) {
        try {
            // 이미지 저장 로직
            String userImgPath = null;
            if (!userImg.isEmpty()) {
                String uniqueFileName = UUID.randomUUID().toString() + "_" + userImg.getOriginalFilename();
                Path uploadPath = Paths.get("C:/lx/final_project/lx-final-project/backend/src/uploads/");
                Files.createDirectories(uploadPath);
                Path filePath = uploadPath.resolve(uniqueFileName);
                userImg.transferTo(filePath.toFile());
                userImgPath = filePath.toString();
            }

            // 사용자 객체 생성 및 저장
            UserVo user = new UserVo();
            user.setUserId(userId);
            user.setUserPw(userPw);
            user.setUserName(userName);
            user.setUserSsn(userSsn);
            user.setUserMobile(userMobile);
            user.setUserEmail(userEmail);
            user.setUserPerm(userPerm);
            user.setRegionPk(regionPk);
            user.setRegionPk2(regionPk2);
            user.setUserImg(userImgPath);

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
    
    // 유저 이름 조회 (GET 요청)
    @GetMapping("/userName/{userPk}")
    public String getUserName(@PathVariable int userPk) {
        try {
            return userService.getUserNameByUserPk(userPk); // userName 리턴
        } catch (Exception e) {
            e.printStackTrace(); // 서버에서 발생한 예외 로그 출력
            return "Error fetching user name";
        }
    }
    
    // 아이디 중복 확인 API
    @GetMapping("/check_userId")
    public boolean checkUserId(@RequestParam("userId") String userId) {
        return userService.checkUserId(userId);
    }
}
