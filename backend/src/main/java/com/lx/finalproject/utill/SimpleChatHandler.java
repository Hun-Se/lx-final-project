package com.lx.finalproject.utill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.lx.finalproject.vo.ChatMessageVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
@Component
@RequiredArgsConstructor 
public class SimpleChatHandler extends TextWebSocketHandler {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleChatHandler.class);

    private final List<WebSocketSession> sessions = new ArrayList<>();
    private final Gson gson; 

    public void broadcast(String message) throws Exception {
        for (WebSocketSession connected : sessions) {
            connected.sendMessage(new TextMessage(message));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        log.info("connected with session id: {}, total sessions: {}", session.getId(), sessions.size());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessageVo chatMessage = gson.fromJson(payload, ChatMessageVo.class);
        log.info("received: {}", payload);
        for (WebSocketSession connected : sessions) {
            connected.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("connection with {} closed", session.getId());
        sessions.remove(session);
    }
}



