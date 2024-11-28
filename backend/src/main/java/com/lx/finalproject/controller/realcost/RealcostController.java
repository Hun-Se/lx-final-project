package com.lx.finalproject.controller.realcost;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lx.finalproject.service.realcost.RealcostService;
import com.lx.finalproject.vo.RealcostVO;

@RestController
@RequestMapping("/api/realcost")
public class RealcostController {

    private static final Logger logger = LoggerFactory.getLogger(RealcostController.class);

    @Autowired
    private RealcostService realcostService;

    @GetMapping("/coordinates")
    public List<RealcostVO> getRealCostCoordinates() {
        List<RealcostVO> coordinates = realcostService.getAllRealCostWithCoordinates();
        
        logger.info("===========================");
        logger.info(coordinates.get(0).getGeom());
        logger.info("===========================");

        return coordinates;
    }
}
