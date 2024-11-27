package com.lx.finalproject.utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.lx.finalproject.service.websocket.WebsocketService;
import com.lx.finalproject.vo.ChatmesVO;

import java.sql.Timestamp;
import java.util.Map;

@Controller
public class WebSoketMapping {

    private static final Logger log = LoggerFactory.getLogger(WebSoketMapping.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private WebsocketService websocketService;

    /**
     * WebSocket 메시지를 처리하는 메서드
     *
     * @param chatmesVO   클라이언트로부터 받은 채팅 메시지 VO
     * @param headers     WebSocket 헤더
     * @param nativeHeaders 추가 헤더 정보
     */
    @MessageMapping("/chat")
    public void sendChat(
            ChatmesVO chatmesVO,
            @Headers Map<String, Object> headers,
            @Header(name = "nativeHeaders", required = false) Map<String, String> nativeHeaders
    ) {
        // 로그로 메시지와 헤더 출력
        log.info("Received chat message: {}", chatmesVO);
        log.info("Headers: {}", headers);
        log.info("Native Headers: {}", nativeHeaders != null ? nativeHeaders : "No native headers");

        // 현재 시간 설정
        if (chatmesVO.getChatmesDatetime() == null) {
            chatmesVO.setChatmesDatetime(new Timestamp(System.currentTimeMillis()));
        }


        // 고정 chat_pk 설정 (기본값: 1)
        if (chatmesVO.getChatPk() == null) {
            chatmesVO.setChatPk(1);
        }

        // 메시지를 저장 (DB에 저장)
        try {
            websocketService.saveChatMessage(chatmesVO);
            log.info("Message saved to database: {}", chatmesVO);
        } catch (Exception e) {
            log.error("Failed to save message to database: {}", e.getMessage(), e);
            return;
        }

        // 메시지를 전송 (WebSocket 브로드캐스트)
        try {
            simpMessagingTemplate.convertAndSend(
                    String.format("/topic/%s", chatmesVO.getChatPk()), // 고정 chat_pk
                    chatmesVO
            );
            log.info("Message sent to topic /topic/{}", chatmesVO.getChatPk());
        } catch (Exception e) {
            log.error("Failed to send message via WebSocket: {}", e.getMessage(), e);
        }
    }
}
