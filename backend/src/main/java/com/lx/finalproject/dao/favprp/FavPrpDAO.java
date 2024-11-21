package com.lx.finalproject.dao.favprp;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.vo.PrpVO;

@Mapper
public interface FavPrpDAO {
    List<PrpVO> getFavprpByUserPk(@Param("userPk") int userPk);
}
