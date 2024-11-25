package com.lx.finalproject.controller.realcost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.finalproject.service.realcost.RealcostService;
import com.lx.finalproject.vo.RealcostVO;

@RestController
@RequestMapping("/api")
public class RealcostController {

    @Autowired
    private RealcostService realcostService;

    @GetMapping("/realcost")
    public ResponseEntity<List<RealcostVO>> getRealCosts() {
        try {
            List<RealcostVO> realCostData = realcostService.getAllRealCost();
            return ResponseEntity.ok(realCostData);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 출력
            return ResponseEntity.status(500).body(null); // 500 오류 반환
        }
    }

}
