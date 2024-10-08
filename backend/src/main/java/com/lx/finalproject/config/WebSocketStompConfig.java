package com.lx.finalproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.lx.finalproject.utill.SimpleChatHandler;

@Configuration
@EnableWebSocket

//  Handler를 웹 소켓 통신에 사용하겠다는 설정을 진행
public class WebSocketStompConfig implements WebSocketConfigurer {
    private final SimpleChatHandler simpleChatHandler;

    public WebSocketStompConfig(
            SimpleChatHandler simpleChatHandler
    ) {
        this.simpleChatHandler = simpleChatHandler;
    }

    @Override
    // WebSocketHandler 객체를 등록하기 위한 메소드
    // 어떤 주소에 어떤 핸들러를 활용할지를 정의하는 메소드
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(simpleChatHandler, "ws/chat")
                .setAllowedOrigins("*");
    }
}