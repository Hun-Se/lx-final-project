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
@RestController
@RequestMapping("/api/auction")
public class AuctionContoller {

	@Autowired
	private AuctionService AuctionService;

	// 중개수수료 계산 엔드포인트 
	@GetMapping("/calculate")
	// deposit = 보증금
	// monthlyRent = 월세
	// transactionType = 거래유형
	// area = 건축물면적
	// buildingArea = 주택면적
	public AuctionFeeDTO calculateFees(@RequestParam double deposit, @RequestParam double price,
			@RequestParam Integer transactionType, @RequestParam double area, @RequestParam double buildingArea) {

		// 보증금과 월차임을 기반으로 거래금액 계산
		double transactionAmount = AuctionService.calculateTransactionAmount(deposit, price, transactionType);

		// 거래금액과 면적을 기반으로 중개수수료 계산
		double brokerageFee = AuctionService.calculateBrokerageFee(transactionType, transactionAmount);

		// 부가세 계산
		double vat = AuctionService.calculateVAT();

		// DTO로 결과 반환
		AuctionFeeDTO feeEstimate = new AuctionFeeDTO(brokerageFee, vat);
		return feeEstimate;
	}
}
