package com.lx.finalproject.service.realcost;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.realcost.RealcostDAO;
import com.lx.finalproject.vo.RealcostVO;

@Service
public class RealcostService {

    @Autowired
    private RealcostDAO realcostDAO;

    // WKT 데이터를 포함한 실거래 데이터를 반환
    public List<RealcostVO> getAllRealCostWithCoordinates() {
        return realcostDAO.getAllRealCostWithCoordinates();
    }
}
