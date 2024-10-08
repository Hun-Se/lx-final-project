package com.lx.finalproject.controller.property;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lx.finalproject.service.property.PropertyService;
import com.lx.finalproject.vo.PrpVO;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService service;
    
    @GetMapping
    public List<PrpVO> getPropertiesAll() {
        return service.getAllProperties();
    }
    

    // 매물 리스트 가져오기
    @GetMapping("/agent")
    public List<PrpVO> getPropertiesByAgent(@RequestParam("agentPk") int agentPk) {
        return service.getAllPropertiesByAgent(agentPk);
    }

    // 특정 매물 가져오기
    @GetMapping("/{prpPk}")
    public PrpVO getPropertyById(@PathVariable int prpPk) {
        return service.getPropertyById(prpPk);
    }

    // 매물 저장
    @PostMapping("/save")
    public void saveProperty(@RequestBody PrpVO property) {
    	service.saveProperty(property);
    }

    // 매물 업데이트
    @PutMapping("/update")
    public void updateProperty(@RequestBody PrpVO property) {
    	service.updateProperty(property);
    }

    // 매물 삭제
    @DeleteMapping("/delete/{prpPk}")
    public void deleteProperty(@PathVariable int prpPk) {
    	service.deleteProperty(prpPk);
    }
}
