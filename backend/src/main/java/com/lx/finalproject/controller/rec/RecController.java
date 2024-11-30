package com.lx.finalproject.controller.rec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // 올바른 Path 임포트
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.lx.finalproject.dto.rec.RecScriptDTO;
import com.lx.finalproject.service.rec.RecService;
import com.lx.finalproject.service.createaccount.UserService;
import com.lx.finalproject.service.rec.RecService;
import com.lx.finalproject.vo.AiRecDecsVO;
import com.lx.finalproject.vo.UserVo;

@RestController
@RequestMapping("/api/audio")
public class RecController {
	
	@Autowired
	private RecService recService;

	// 파일 저장 경로 지정
	private static final String UPLOAD_DIR = "/Users/sehun/Desktop/lx-final-project/frontend/public/assets/record";

	@PostMapping("/upload")
	public ResponseEntity<String> uploadAudioFile(
			@RequestParam(value = "file", required = false) MultipartFile file, // 파일 필수가 아님
			@RequestParam("flr_pk") int flrPk) {
		try {
			if (file != null && !file.isEmpty()) {
				// 이름 맞춰서 경로에 녹취파일 저장
				Path filePath = Paths.get(UPLOAD_DIR, "record_" + flrPk + ".mp3");
				File destFile = new File(filePath.toString());
				file.transferTo(destFile);

				// 파일 경로와 함께 데이터 저장
				recService.saveRec(filePath.toString(), flrPk);

				// Flask 서버 호출
				String flaskUrl = "http://localhost:5000/record/flr/" + flrPk;
				RestTemplate restTemplate = new RestTemplate();
				String flaskResponse = restTemplate.getForObject(flaskUrl, String.class);
				System.out.println("Flask 서버 응답: " + flaskResponse);
			} else {
				System.out.println("파일이 첨부되지 않았으므로 파일 저장 및 Flask 호출은 생략합니다.");
			}

			return ResponseEntity.ok("Request processed successfully");
		} catch (IOException e) {
			return ResponseEntity.status(500).body("Failed to process file: " + e.getMessage());
		}
	}
	// FLR PK(flrPk)를 기반으로 관련된 채팅 PK(recPk)를 조회하는 엔드포인트입
	@GetMapping("/flr/{flrPk}/recPk")
	public ResponseEntity<Integer> getRecPkByFlrPk(@PathVariable int flrPk) {
		Integer recPk = recService.getRecPkByFlrPk(flrPk);
		if (recPk != null) {
			return ResponseEntity.ok(recPk); // Map 없이 chatPk 값만 직접 반환
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// 데이터베이스에서 직접 전체 채팅 수크립트를 가져오는 엔드포인트
	// 특정 flrPk에 대한 전체 채팅 스크립트를 조회하며, AI가 문제로 판단한 스크립트에 대해 하이라이팅 표시
	@GetMapping("/{recPk}/script")
	public ResponseEntity<List<RecScriptDTO>> getRecMessagesWithHighlights(@PathVariable int recPk) {
	    try {
	        List<RecScriptDTO> messages = recService.getRecScriptByRecPkWithHighlights(recPk);
	        return ResponseEntity.ok(messages);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	@GetMapping("/airecdecs/{recPk}")
    public ResponseEntity<AiRecDecsVO> getAiRecDecs(@PathVariable int recPk) {
        AiRecDecsVO aiRecDecs = recService.getAiRecDecsByRecPk(recPk);
        if (aiRecDecs != null) {
            return ResponseEntity.ok(aiRecDecs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
