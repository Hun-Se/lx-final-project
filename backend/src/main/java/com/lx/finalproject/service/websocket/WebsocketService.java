package com.lx.finalproject.service.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.websocketchat.WebsocketDAO;
import com.lx.finalproject.vo.ChatmesVO;

import java.sql.Timestamp;

@Service
public class WebsocketService {

    @Autowired
    private WebsocketDAO websocketDAO;

    /**
     * 채팅 메시지를 저장하는 메서드
     *
     * @param chatmesVO 저장할 메시지 데이터
     */
    public void saveChatMessage(ChatmesVO chatmesVO) {
        // chatmesDatetime이 비어 있으면 현재 시간으로 설정
        if (chatmesVO.getChatmesDatetime() == null) {
            chatmesVO.setChatmesDatetime(new Timestamp(System.currentTimeMillis()));
        }

        // 채팅방 ID를 기본값으로 설정
        if (chatmesVO.getChatPk() == null) {
            chatmesVO.setChatPk(1); // 기본값
        }

        // userPk가 존재할 경우, 해당 userPk로 agentPk를 조회
        if (chatmesVO.getUserPkSender() != null && chatmesVO.getAgentPkSender() == null) {
            Integer agentPk = websocketDAO.findAgentByUserPk(chatmesVO.getUserPkSender());
            chatmesVO.setAgentPkSender(agentPk); // agent_pk를 설정
        }

        // 데이터베이스에 메시지 저장
        websocketDAO.insertChatMessage(chatmesVO);
    }
}
