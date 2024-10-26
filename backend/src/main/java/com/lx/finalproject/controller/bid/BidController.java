package com.lx.finalproject.controller.bid;

import com.lx.finalproject.dao.bid.BidDAO;
import com.lx.finalproject.dto.bid.BidDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bid")
public class BidController {

	@Autowired
	private BidDAO bidDAO;

	@PostMapping("/create")
	public String placeBid(@RequestBody BidDTO agentBidDTO) {
		try {
			// userPk를 기반으로 agentPk를 찾음
			Integer agentPk = bidDAO.getAgentPkByUserPk(agentBidDTO.getAgentPk());
			if (agentPk == null) {
				return "Failed to place bid: Agent not found.";
			}
			// BidDTO에 agentPk 설정
			agentBidDTO.setAgentPk(agentPk);

			// 입찰 저장
			bidDAO.insertBid(agentBidDTO);
			return "Bid placed successfully!";
		} catch (Exception e) {
			return "Failed to place bid: " + e.getMessage();
		}
	}

	// 특정 사용자의 입찰 목록 조회
	@GetMapping("/user/{userPk}")
	public List<BidDTO> getBidsByUserPk(@PathVariable int userPk) {
		return bidDAO.getBidsByUserPk(userPk);
	}
}
