package com.lx.finalproject.service.bid;

import com.lx.finalproject.dto.bid.BidDTO;
import java.util.List;

public interface BidService {
    void placeBid(BidDTO agentBidDTO);
    List<BidDTO> getBidsForAuction(int auctionPk);
    BidDTO getBidDetails(int bidPk);
}
