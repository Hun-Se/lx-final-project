package com.lx.finalproject.dao.auction;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lx.finalproject.dto.auction.AuctionFeeDTO;
import com.lx.finalproject.vo.AuctionVO;
import com.lx.finalproject.dto.auction.AuctionAgentDTO;
import java.util.List;

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
    
    // 특정 사용자 ID에 대한 경매와 매물 정보를 조회하는 메소드
    List<AuctionAgentDTO> getAuctionsWithPropertyByUserPk(@Param("userPk") int userPk);
    
 // 특정 user_pk에 해당하는 중개인이 임장요청한 매물 중 경매가 진행된 목록 조회 메서드
    List<AuctionAgentDTO> getInspectedAuctionsByAgent(@Param("userPk") int userPk);
    
}
