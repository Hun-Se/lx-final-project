package com.lx.finalproject.dao.realcost;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.lx.finalproject.vo.RealcostVO;

@Mapper
public interface RealcostDAO {

    // 모든 real_cost 데이터를 가져오는 메서드
    List<RealcostVO> getAllRealCost();
}
