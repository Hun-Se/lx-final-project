package com.lx.finalproject.dao.realcost;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.lx.finalproject.vo.RealcostVO;

@Mapper
public interface RealcostDAO {
    List<RealcostVO> getAllRealCostGeoJSON();
    List<RealcostVO> getAllRealCostWithCoordinates(); // GeoJSON을 포함한 실거래가 데이터 조회
}
