package com.lx.finalproject.dto.chatGPT;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class ChatGPTRequestDTO {
    @JsonProperty("model")  // JSON 직렬화 시 사용할 필드 이름
    private String model;

    @JsonProperty("messages")  // JSON 직렬화 시 사용할 필드 이름
    private List<ChatGPTMessageDTO> messages;

    public ChatGPTRequestDTO(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new ChatGPTMessageDTO("user", prompt));
    }
}
