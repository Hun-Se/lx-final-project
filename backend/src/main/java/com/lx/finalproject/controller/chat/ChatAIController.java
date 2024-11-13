package com.lx.finalproject.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lx.finalproject.dto.chat.ChatMessageDTO;
import com.lx.finalproject.service.chat.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatAIController {

	@Autowired
	private ChatService chatService;

	private final RestTemplate restTemplate;

	public ChatAIController(ChatService chatService, RestTemplate restTemplate) {
		this.chatService = chatService;
		this.restTemplate = new RestTemplate();
	}
	
	// FLR PK(flrPk)를 기반으로 관련된 채팅 PK(chatPk)를 조회하는 엔드포인트입
	@GetMapping("/{flrPk}/chatPk")
	public ResponseEntity<Integer> getChatPkByFlrPk(@PathVariable int flrPk) {
		Integer chatPk = chatService.getChatPkByFlrPk(flrPk);
		if (chatPk != null) {
			return ResponseEntity.ok(chatPk); // Map 없이 chatPk 값만 직접 반환
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// 데이터베이스에서 직접 전체 채팅 메시지를 가져오는 엔드포인트
	// 특정 flrPk에 대한 전체 채팅 메시지를 조회하며, AI가 문제로 판단한 메시지에 대해 하이라이팅 표시
    @GetMapping("/{flrPk}/messages")
    public ResponseEntity<List<ChatMessageDTO>> getChatMessagesWithHighlights(@PathVariable int flrPk) {
        try {
            // 전체 메시지를 조회
            List<ChatMessageDTO> messages = chatService.getChatMessagesByFlrPk(flrPk);
            // 문제로 판단된 메시지 조회
            List<Integer> problematicMessages = chatService.getProblematicMessagesByFlrPk(flrPk);

            // 각 메시지에 대해 문제 여부를 설정
            for (ChatMessageDTO message : messages) {
                if (problematicMessages.contains(message.getChatmesPk())) {
                    message.setIsProblematic(true); // 문제로 판단된 메시지에 대해 표시
                }
            }

            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

	// Flask 서버로부터 AI가 위반의심건을 판단하고, 그에 대한 대화내용을 추출하여 DB에 저장하는 엔드포인트
	@GetMapping("/flr/{flrPk}")
	public ResponseEntity<String> getChatMessagesFromFlask(@PathVariable int flrPk) {
		// Flask 서버의 URL을 지정
		String flaskUrl = "http://localhost:5000/chat/flr/" + flrPk;

		try {
			// Flask 서버로 GET 요청을 보냄
			ResponseEntity<String> response = restTemplate.getForEntity(flaskUrl, String.class);
			return ResponseEntity.ok(response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving chat messages from Flask server");
		}
	}
}
