package com.lx.finalproject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lx.finalproject.dto.PrpDetailsResponseDTO;
import com.lx.finalproject.dto.PrpFilterRequestDTO;
import com.lx.finalproject.vo.PrpVO;

@Component
public class PrpDAO {

    @Autowired
    SqlSession session;
    
    // 모든 매물 리스트 반환
	public List<PrpDetailsResponseDTO> getPrpList() {
		List<PrpDetailsResponseDTO> prpList = session.selectList("getPrpList");
		return prpList;
	}
    
    // 요청받은 필터 조건으로 해당하는 매물 리스트 반환
    public List<PrpDetailsResponseDTO> getPrpListByFilter(PrpFilterRequestDTO filter) {
    	List<PrpDetailsResponseDTO> prpFilterList = session.selectList("getPrpListByFilter", filter);
    	return prpFilterList;
    }


    
}