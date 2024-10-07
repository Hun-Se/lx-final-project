package com.lx.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.PrpDAO;
import com.lx.finalproject.dto.PrpDetailsResponseDTO;
import com.lx.finalproject.dto.PrpFilterRequestDTO;
import com.lx.finalproject.vo.PrpVO;

@Service
public class PrpService {

    @Autowired
    private PrpDAO prpDAO;
    
    // 모든 매물을 조회
	public List<PrpDetailsResponseDTO> getPrpList() {
		return prpDAO.getPrpList();
	}

    // 필터 조건에 맞는 매물 조회
    public List<PrpDetailsResponseDTO> getPrpListByFilter(PrpFilterRequestDTO filter) {
        return prpDAO.getPrpListByFilter(filter);
    }


}