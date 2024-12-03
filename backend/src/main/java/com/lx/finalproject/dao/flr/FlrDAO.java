package com.lx.finalproject.dao.flr;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.dto.chat.ChatMessageDTO;
import com.lx.finalproject.dto.flr.FlrDTO;

import java.util.List;

@Mapper
public interface FlrDAO {
	void insertFlrData(FlrDTO flrDto);
	
	// flrPk를 기반으로 recPk가 NULL인지 확인하는 메서드
    Integer checkRecPkByFlrPk(@Param("flrPk") int flrPk);
}
