package com.lx.finalproject.service.winning;

import com.lx.finalproject.dto.bid.BidDTO;
import java.util.List;

public interface WinningService {
	void placeBid(BidDTO agentBidDTO);

	List<BidDTO> getBidsForAuction(int auctionPk);

	BidDTO getBidDetails(int bidPk);

}
