package com.lx.finalproject.service.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.chat.ChatDAO;
import com.lx.finalproject.dto.chat.ChatMessageDTO;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatDAO chatDAO;

    public List<ChatMessageDTO> getChatMessagesByFlrPk(int flrPk) {
        return chatDAO.findMessagesByFlrPk(flrPk);
    }
    
    public ChatService(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    public Integer getChatPkByFlrPk(int flrPk) {
    	Integer chatPk = chatDAO.findChatPkByFlrPk(flrPk);
        System.out.println("Retrieved chatPk for flrPk " + flrPk + ": " + chatPk);
        return chatPk;
    }

    public List<Integer> getProblematicMessagesByFlrPk(int flrPk) {
        return chatDAO.findProblematicMessagesByFlrPk(flrPk);
    }
    
}
