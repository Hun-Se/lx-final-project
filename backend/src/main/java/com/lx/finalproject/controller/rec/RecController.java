package com.lx.finalproject.controller.rec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // 올바른 Path 임포트
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.lx.finalproject.service.createaccount.UserService;
import com.lx.finalproject.service.rec.RecService;
import com.lx.finalproject.vo.UserVo;

@RestController
@RequestMapping("/api/audio")
public class RecController {
	
	// 파일 저장 경로 지정
	private static final String UPLOAD_DIR = "C:/lx-final-project/frontend/public/assets/record";

    @Autowired
    private RecService recService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAudioFile(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("flr_pk") int flrPk,
                                                  @RequestParam("user_pk") int userPk,
                                                  @RequestParam("prp_pk") int prpPk,
                                                  @RequestParam("agent_pk") int agentPk) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is required");
        }

        try {
            // 이름 맞춰서 경로에 녹취파일 저장
            Path filePath = Paths.get(UPLOAD_DIR, "record_" + flrPk + ".mp3");
            File destFile = new File(filePath.toString());
            file.transferTo(destFile);
            
            // 현재 날짜 가져오기
            LocalDate currentDate = LocalDate.now();

            // 파일 경로와 함께 데이터 저장
            recService.saveRec(filePath.toString(), flrPk, userPk, prpPk, agentPk);
            
            // Flask 서버 호출
            String flaskUrl = "http://localhost:5000/record/flr/" + flrPk;
            RestTemplate restTemplate = new RestTemplate();
            String flaskResponse = restTemplate.getForObject(flaskUrl, String.class);
            System.out.println("Flask 서버 응답: " + flaskResponse);

            return ResponseEntity.ok("File uploaded and saved to database successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to save file: " + e.getMessage());
        }
    }
}
