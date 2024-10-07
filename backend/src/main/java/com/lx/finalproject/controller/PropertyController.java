package com.lx.finalproject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lx.finalproject.service.PropertyService;
import com.lx.finalproject.vo.PropertyVo;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    // 모든 매물 조회
    @GetMapping
    public List<PropertyVo> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // 특정 매물 조회
    @GetMapping("/{prp_pk}")
    public PropertyVo getPropertyById(@PathVariable Long prp_pk) {
        return propertyService.getPropertyById(prp_pk);
    }
}
