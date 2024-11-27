package com.lx.finalproject.dao.websocketchat;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.vo.ChatmesVO;

@Mapper
public interface WebsocketDAO {

    // 채팅 메시지 저장
    void insertChatMessage(ChatmesVO chatmesVO);

    // userPk로 agent_pk 조회
    Integer findAgentByUserPk(@Param("userPk") Integer userPk);
}
