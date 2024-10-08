package com.lx.finalproject.utill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.lx.finalproject.vo.ChatMessageVo;

import org.springframework.messaging.handler.annotation.Headers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class WebSoketMapping {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleChatHandler.class);

    @Autowired 
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat")

    
    public void sendChat(
    		
            ChatMessageVo chatMessage,
            @Headers Map<String, Object> headers,
            @Header("nativeHeaders") Map<String, String> nativeHeaders
    ) {
    	
        log.info("Received chat message: {}", chatMessage);
        log.info("Headers: {}", headers);
        log.info("Native Headers: {}", nativeHeaders);

        // 현재 시간 설정
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        chatMessage.setTime(time);
        
        

        // 메시지를 전송
        simpMessagingTemplate.convertAndSend(
                String.format("/topic/%s", chatMessage.getRoomId()), 
                chatMessage
        );
    }
}
