package com.lx.finalproject.dao;

import java.util.List;

import com.lx.finalproject.dto.prp.PrpDTO;

public interface PropertyDao {
	
	// 매물 리스트 가져오기
    List<PrpDTO> getAllProperties();
    
    
    PrpDTO getPropertyMarkerById(Long prp_pk);
    
    
}