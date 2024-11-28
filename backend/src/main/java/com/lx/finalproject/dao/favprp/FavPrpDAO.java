package com.lx.finalproject.dao.favprp;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.vo.FavPrpVO;
import com.lx.finalproject.vo.PrpVO;

@Mapper
public interface FavPrpDAO {
	// 관심 매물 조회 AI용
    List<PrpVO> getFavprpByUserPk(@Param("userPk") int userPk);
    
    // 관심 매물 추가
    void addFavprp(FavPrpVO favPrpVO);

    // 관심 매물 삭제
    void deleteFavprp(FavPrpVO favPrpVO);
}
