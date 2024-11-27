package com.lx.finalproject.controller.WebSocketChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.lx.finalproject.service.websocket.WebsocketService;
import com.lx.finalproject.vo.ChatmesVO;

@Controller
@RequestMapping("chat")
@CrossOrigin(origins = "http://localhost:5173") // 허용할 Origin 명시
public class ChatController {

    @Autowired
    private WebsocketService websocketService;

    /**
     * 채팅 메시지 저장 엔드포인트
     *
     * @param chatmesVO 요청 본문에 포함된 메시지 데이터
     * @return 처리 결과 메시지
     */
    @PostMapping("save")
    public @ResponseBody String saveChatMessage(@RequestBody ChatmesVO chatmesVO) {
        try {
            // user_pk_sender 또는 agent_pk_sender 중 하나는 반드시 존재해야 함
            if (chatmesVO.getUserPkSender() == null && chatmesVO.getAgentPkSender() == null) {
                return "Error: Either userPkSender or agentPkSender is required.";
            }

            // 메시지를 저장
            websocketService.saveChatMessage(chatmesVO);
            return "Message saved successfully.";
        } catch (Exception e) {
            return "Failed to save message: " + e.getMessage();
        }
    }
}
