package com.lx.finalproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lx.finalproject.dao.PropertyDao;
import com.lx.finalproject.vo.PropertyVo;

@Service
public class PropertyService {
    @Autowired
    private PropertyDao propertyDao;

    public List<PropertyVo> getAllProperties() {
        return propertyDao.getAllProperties();
    }

    public PropertyVo getPropertyById(Long prp_pk) {
        return propertyDao.getPropertyById(prp_pk);
    }
}
