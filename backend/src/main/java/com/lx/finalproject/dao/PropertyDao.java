package com.lx.finalproject.dao;

import java.util.List;
import com.lx.finalproject.vo.PropertyVo;

public interface PropertyDao {
	
	// 매물 리스트 가져오기
    List<PropertyVo> getAllProperties();
    
    
    PropertyVo getPropertyById(Long prp_pk);
}