package com.lx.finalproject.controller.chatGPT;

import com.lx.finalproject.dto.chatGPT.AiChatbotResponseDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


//chatbot api 생성
@RestController
@RequestMapping("/api/chatbot")
public class AIChatbotController {

    private final RestTemplate restTemplate;

    public AIChatbotController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostMapping
    public ResponseEntity<?> handleChatbotRequest(@RequestBody Map<String, String> requestBody) {
        String flaskUrl = "http://localhost:5000/chatbot"; // Flask 서버 URL
        String userQuestion = requestBody.get("question");

        if (userQuestion == null || userQuestion.isEmpty()) {
            return ResponseEntity.badRequest().body("질문을 입력해주세요.");
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, String> flaskRequest = Map.of("question", userQuestion);
            HttpEntity<Map<String, String>> request = new HttpEntity<>(flaskRequest, headers);

            ResponseEntity<AiChatbotResponseDTO> response = restTemplate.postForEntity(flaskUrl, request, AiChatbotResponseDTO.class);
            System.out.println(response.getBody());
            AiChatbotResponseDTO aiChatbotResponseDTO = response.getBody();
            return ResponseEntity.ok(aiChatbotResponseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Flask 서버 요청 중 문제가 발생했습니다: " + e.getMessage());
        }
    }
}
