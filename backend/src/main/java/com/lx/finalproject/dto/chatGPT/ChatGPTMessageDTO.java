package com.lx.finalproject.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTMessageDTO {
    private String role;    // "user" 또는 "assistant"
    private String content; // 메시지 내용

    // 역할과 내용을 기반으로 객체를 생성할 수 있는 생성자
    public ChatGPTMessageDTO(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getContent() {
        return content; 
    }
}
