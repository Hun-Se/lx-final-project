package com.lx.finalproject.dao.auction;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.dto.auction.AuctionFeeDTO;
import com.lx.finalproject.vo.AuctionVO;

@Mapper
public interface AuctionDAO {
	
	// 경매 데이터를 저장하는 메소드
    void saveAuction(AuctionVO auctionVO);
    
    // 특정 경매 ID로 경매 데이터를 가져오는 메소드
    AuctionVO getAuctionById(@Param("auctionPk") int auctionPk);
    
    // 중개수수료 계산 관련 데이터를 저장하는 메소드
    void saveAuctionFee(AuctionFeeDTO auctionFeeDTO);

    // 특정 경매 ID로 중개수수료 데이터를 가져오는 메소드
    AuctionFeeDTO getAuctionFeeById(@Param("auctionPk") int auctionPk);
}
