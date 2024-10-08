package com.lx.finalproject.service.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.auction.AuctionDAO;
import com.lx.finalproject.dto.auction.AuctionFeeDTO;
import com.lx.finalproject.vo.AuctionVO;


@Service
public class AuctionService {
	
	@Autowired
    private AuctionDAO AuctionDAO;
	
	double fee;

	// 거래유형에 따른 거래 금액 계산
	// - transactionType = 거래유형
	// - deposit = 보증금
	// - price = 금액(전세금, 월세금, 매매금)
	public double calculateTransactionAmount(double deposit, double price, Integer transactionType) {
		double transactionAmount = 0.0;

		switch (transactionType) {
		case 0: // 매매
			transactionAmount = price; // 매매는 매매금액
			break;
		case 1: // 전세
			transactionAmount = price; // 전세는 전세금액
			break;
		case 2: // 월세
			// 보증금 + (월차임 × 100)이 5천만 원 미만인 경우
			if (deposit + (price * 100) < 50000000) {
				transactionAmount = deposit + (price * 70); // 보증금 + (월차임 × 70)
			} else {
				transactionAmount = deposit + (price * 100); // 보증금 + (월차임 × 100)
			}
			break;
		default:
			throw new IllegalArgumentException("Invalid transaction type");
		}
		return transactionAmount;
	}

	// 거래금액과 면적을 기반으로 중개수수료 계산
	// - transactionType = 거래유형
	// - transactionAmount = 거래금액
	public double calculateBrokerageFee(Integer transactionType, double transactionAmount) {
		double feeRate = 0.0;
		double maxFee = 0.0;

		// 1. 거래유형에 따른 기본 수수료율 계산
		if (transactionType == 0) { // 매매
			if (transactionAmount < 50000000) {
				feeRate = 0.006;
				maxFee = 250000;
			} else if (transactionAmount < 200000000) {
				feeRate = 0.005;
				maxFee = 800000;
			} else if (transactionAmount < 900000000) {
				feeRate = 0.004;
			} else if (transactionAmount < 1200000000) {
				feeRate = 0.005;
			} else if (transactionAmount < 1500000000) {
				feeRate = 0.006;
			} else {
				feeRate = 0.007;
			}
		} else if (transactionType == 1 || transactionType == 2) { // 전세 또는 월세
			if (transactionAmount < 50000000) {
				feeRate = 0.005;
				maxFee = 200000;
			} else if (transactionAmount < 100000000) {
				feeRate = 0.004;
				maxFee = 300000;
			} else {
				feeRate = (transactionType == 1) ? 0.003 : 0.004; // 전세와 월세의 기본 상한 요율 차이 반영
			}
		}

//		// 0. 건축물 면적과 주택 면적의 비율에 따른 수수료율 조정
//        if (area >= (buildingArea / 2)) {
//            // 주택 중개보수 요율 적용 (기본 요율에서 조정)
//            feeRate = 0.005;  // 주택 상한 요율로 조정
//        } else if (area < (buildingArea / 2)) {
//            // 주택 외 중개보수 요율 적용
//            feeRate = 0.009;  // 주택 외 상한 요율로 조정
//        }

        // 2. 기본 중개 수수료 계산
        fee = transactionAmount * feeRate;

        // 3. 한도액 초과 시 한도액 적용
        if (maxFee > 0.0 && fee > maxFee) {
            fee = maxFee;
        }

        return fee;
	}

	// 부가세 계산
	public double calculateVAT() {
		double brokerageFee = fee * 0.1; // 부가세 10%
		return brokerageFee;
	}
	
	// 경매 데이터 저장
    public void saveAuction(AuctionVO auctionVO) {
        AuctionDAO.saveAuction(auctionVO);
    }

    // 경매 수수료 데이터 저장
    public void saveAuctionFee(AuctionFeeDTO auctionFeeDTO) {
        AuctionDAO.saveAuctionFee(auctionFeeDTO);
    }

    // 경매 데이터 조회
    public AuctionVO getAuctionById(int auctionPk) {
        return AuctionDAO.getAuctionById(auctionPk);
    }

    // 경매 수수료 데이터 조회
    public AuctionFeeDTO getAuctionFeeById(int auctionPk) {
        return AuctionDAO.getAuctionFeeById(auctionPk);
    }

}
