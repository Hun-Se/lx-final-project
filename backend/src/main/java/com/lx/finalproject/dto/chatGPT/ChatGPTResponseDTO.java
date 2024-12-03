package com.lx.finalproject.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTResponseDTO {
    private List<Choice> choices;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private int index;
        private ChatGPTMessageDTO message;

        // getMessage() 메소드를 올바르게 구현
        public ChatGPTMessageDTO getMessage() {
            return message; // message 필드를 반환
        } 
    }

    public List<Choice> getChoices() {
        return choices; 
    }
}
