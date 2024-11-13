package com.lx.finalproject.dao.chat;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.dto.chat.ChatMessageDTO;

import java.util.List;

@Mapper
public interface ChatDAO {
	
	List<ChatMessageDTO> findMessagesByFlrPk(@Param("flrPk") int flrPk);
    
    Integer findChatPkByFlrPk(@Param("flrPk") int flrPk);
    
    List<Integer> findProblematicMessagesByFlrPk(@Param("flrPk") int flrPk);
}
