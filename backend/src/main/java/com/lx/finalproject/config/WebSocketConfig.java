package com.lx.finalproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.lx.finalproject.utill.SimpleChatHandler;

// STOMP: Streaming Text Oriented Messaging Protocol ->  HTTP와 같은 구조의 메세지 형식을 갖추어서 보내도록 하는 통신 규약
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // STOMP 엔드포인트 설정용 메소드
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatting");
    }

    @Override
    // MessageBroker를 활용하는 방법 설정
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");


    }
}