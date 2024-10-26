package com.lx.finalproject.controller.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.finalproject.dto.auction.AuctionAgentDTO;
import com.lx.finalproject.dto.auction.AuctionFeeDTO;
import com.lx.finalproject.service.auction.AuctionService;
import com.lx.finalproject.service.property.PropertyService;
import com.lx.finalproject.vo.PrpVO;

@RestController
@RequestMapping("/api/auction")
public class AuctionContoller {

	@Autowired
	private AuctionService auctionService;

	@Autowired
	private PropertyService propertyService;

	// 사용자 ID를 이용한 경매 및 매물 정보 조회 엔드포인트
	@GetMapping("/user/{userPk}")
	public List<AuctionAgentDTO> getAuctionsByUserPk(@PathVariable int userPk) {
		return auctionService.getAuctionsWithPropertyByUserPk(userPk);
	}

	// 중개수수료 계산 엔드포인트
	@GetMapping("/calculate/{prpPk}")
	public AuctionFeeDTO calculateFeesByPropertyId(@PathVariable int prpPk) {
		// 매물 정보를 가져옴
		PrpVO property = propertyService.getPropertyById(prpPk);

		// 매물 정보에서 필요한 값 추출
		// deposit = 보증금
		// price = 금액(매매금, 전세금, 월세금)
		// transactionType = 거래유형
		// exclArea = 전용면적
		// prpType = 매물유형(주택, 오피스텔, 그외)
		// 매물 ID로 중개수수료 계산
		double deposit = property.getPrpDeposit() != null ? property.getPrpDeposit() : 0;
		double price = property.getPrpPrice() != null ? property.getPrpPrice() : 0;
		int transactionType = property.getPrpTransType();
		double exclArea = property.getPrpExclArea();
		int prpType = property.getPrpType();

		// 거래금액 계산
		double transactionAmount = auctionService.calculateTransactionAmount(deposit, price, transactionType);

		// 상한 요율 계산
		double feeRate = auctionService.calculateFeeRate(prpType, transactionType, transactionAmount, exclArea);

		// MAXFEE 설정
		double maxFee = determineMaxFee(transactionAmount, prpType, transactionType);

		// 중개수수료 계산 (MAXFEE 적용)
		double brokerageFee = auctionService.calculateBrokerageFee(transactionAmount, feeRate, maxFee);

		// 부가세 계산
		double vat = auctionService.calculateVAT(brokerageFee);

		// 디버깅 로그 추가
		System.out.println("Calculated values:");
		System.out.println("Transaction Amount: " + transactionAmount);
		System.out.println("Fee Rate: " + feeRate);
		System.out.println("Brokerage Fee: " + brokerageFee);
		System.out.println("VAT: " + vat);
		System.out.println("Total Fee: " + (brokerageFee + vat));

		// DTO로 결과 반환
		return new AuctionFeeDTO(brokerageFee, vat, feeRate);
	}

	// MAXFEE를 결정하는 메서드
	private double determineMaxFee(double transactionAmount, int prpType, int transactionType) {
		double maxFee = 0.0;

		if (prpType == 0 && transactionType == 0) { // 주택 매매
			if (transactionAmount < 50000000)
				maxFee = 250000;
			else if (transactionAmount < 200000000)
				maxFee = 800000;
		} else if (prpType == 0 && (transactionType == 1 || transactionType == 2)) { // 주택 전세/월세
			if (transactionAmount < 50000000)
				maxFee = 200000;
			else if (transactionAmount < 100000000)
				maxFee = 300000;
		}
		// 추가적인 조건에 따라 다른 MAXFEE 설정 가능
		return maxFee;
	}

	// 중개인이 임장 요청한 매물 중 경매가 진행된 목록 조회 엔드포인트
	@GetMapping("/agent/{userPk}/inspected-auctions")
	public List<AuctionAgentDTO> getInspectedAuctionsByAgent(@PathVariable int userPk) {
	    List<AuctionAgentDTO> auctions = auctionService.getInspectedAuctionsByAgent(userPk);
	    auctions.forEach(auction -> System.out.println("Auction: " + auction.toString()));
	    return auctions;
	}

}