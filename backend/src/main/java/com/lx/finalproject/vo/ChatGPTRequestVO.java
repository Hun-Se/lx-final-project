package com.lx.finalproject.vo;

import java.util.ArrayList;
import java.util.List;

import com.lx.finalproject.dto.chatGPT.ChatGPTMessageDTO;

public class ChatGPTRequestVO {

    private String model;
    private List<ChatGPTMessageDTO> messages;  // DTO와 일관되게 사용
    private int maxTokens;

    // 기본 생성자
    public ChatGPTRequestVO() {
        this.messages = new ArrayList<>();
    }

    // 생성자
    public ChatGPTRequestVO(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new ChatGPTMessageDTO("user", prompt));  // ChatGPTMessageDTO 사용
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatGPTMessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatGPTMessageDTO> messages) {
        this.messages = messages;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }
}
