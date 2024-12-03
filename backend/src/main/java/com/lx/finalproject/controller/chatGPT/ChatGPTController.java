package com.lx.finalproject.controller.chatGPT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lx.finalproject.dto.chatGPT.ChatGPTRequestDTO;
import com.lx.finalproject.dto.chatGPT.ChatGPTResponseDTO;

@RestController
@RequestMapping("/bot")
public class ChatGPTController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt") String prompt) {
        ChatGPTRequestDTO request = new ChatGPTRequestDTO(model, prompt);
        
        // API 호출
        ChatGPTResponseDTO chatGPTResponse = restTemplate.postForObject(apiURL, request, ChatGPTResponseDTO.class);
        
        // null 체크를 통해 에러 방지
        if (chatGPTResponse != null && chatGPTResponse.getChoices() != null && !chatGPTResponse.getChoices().isEmpty()) {
            return chatGPTResponse.getChoices().get(0).getMessage().getContent();
        } else {
            return "No response from ChatGPT.";
        }
    }
}
