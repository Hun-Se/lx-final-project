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

    // 실거래가 데이터를 가져오는 메소드
    public List<RealcostVO> getAllRealCost() {
        try {
            // 데이터베이스에서 실거래가 데이터를 가져옵니다.
            return realcostDAO.getAllRealCost();
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            e.printStackTrace();
            // 예외를 RuntimeException으로 던지며, 메시지를 추가하여 클라이언트에 알림
            throw new RuntimeException("실거래가 데이터를 가져오는 데 실패했습니다.");
        }
    }
}
