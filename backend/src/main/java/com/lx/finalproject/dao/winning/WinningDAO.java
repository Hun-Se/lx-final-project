package com.lx.finalproject.dao.winning;

import com.lx.finalproject.dto.bid.BidDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface WinningDAO {
	void insertBid(BidDTO BidDTO);

	List<BidDTO> getBidsByAuctionPk(int auctionPk);

	BidDTO getBidById(int bidPk);

	// userPk로 agentPk 가져오기
	Integer getAgentPkByUserPk(int userPk);

	// userPk로 사용자의 입찰 목록 조회
	List<BidDTO> getBidsByUserPk(int userPk);
	
	// 특정 사용자의 입찰 개수 조회
    int getBidCountByUserPk(int userPk);
}
