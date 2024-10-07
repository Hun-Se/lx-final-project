package com.lx.finalproject.dao;

import java.util.List;
import com.lx.finalproject.vo.PropertyVo;

public interface PropertyDao {
    List<PropertyVo> getAllProperties();
    PropertyVo getPropertyById(Long prp_pk);
}