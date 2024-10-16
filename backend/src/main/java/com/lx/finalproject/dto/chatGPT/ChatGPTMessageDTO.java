package com.lx.finalproject.dto.chatGPT;

import java.util.Objects;

public class ChatGPTMessageDTO {
    private String role;    // "user" 또는 "assistant"
    private String content; // 메시지 내용

    // 기본 생성자 (NoArgsConstructor 대체)
    public ChatGPTMessageDTO() {
    }

    // 모든 필드를 받는 생성자 (AllArgsConstructor 대체)
    public ChatGPTMessageDTO(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // Getter 및 Setter 메서드
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
