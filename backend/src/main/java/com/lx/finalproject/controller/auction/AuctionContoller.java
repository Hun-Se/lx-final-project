package com.lx.finalproject.controller.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

		// 보증금과 월차임을 기반으로 거래금액 계산
		double transactionAmount = auctionService.calculateTransactionAmount(deposit, price, transactionType);

		// 거래금액과 매물유형, 전용면적을 기반으로 중개수수료 계산
		double brokerageFee = auctionService.calculateBrokerageFee(prpType, transactionType, transactionAmount,
				exclArea);

		// 부가세 계산
		double vat = auctionService.calculateVAT();

		// DTO로 결과 반환
		return new AuctionFeeDTO(brokerageFee, vat);
	}
}