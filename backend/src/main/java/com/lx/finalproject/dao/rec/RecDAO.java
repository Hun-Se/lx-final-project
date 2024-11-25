package com.lx.finalproject.dao.rec;

import com.lx.finalproject.dto.bid.BidDTO;
import com.lx.finalproject.vo.RecVO;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RecDAO {
	void insertRec(RecVO rec);
}
