package com.lx.finalproject.dao.property;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.lx.finalproject.vo.PrpVO;

@Mapper
public interface PropertyDAO {

    // 매물 리스트 가져오기
    List<PrpVO> getAllPropertiesByAgent(@Param("agentPk") int agentPk);

    // 특정 매물 가져오기
    PrpVO getPropertyById(@Param("prpPk") int prpPk);

    // 매물 저장
    void saveProperty(PrpVO property);

    // 매물 업데이트
    void updateProperty(PrpVO property);

    // 매물 삭제
    void deleteProperty(@Param("prpPk") int prpPk);

    // 매물 전체가저오기
	List<PrpVO> getAllProperties();
}
