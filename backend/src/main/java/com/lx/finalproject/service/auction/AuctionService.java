package com.lx.finalproject.service.auction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.auction.AuctionDAO;
import com.lx.finalproject.dto.auction.AuctionAgentDTO;
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
	
	 // 상한 요율 계산
    public double calculateFeeRate(Integer prpType, Integer transactionType, double transactionAmount, double prpExclArea) {
        double feeRate = 0.0;
        if (prpType == 0) {
            if (transactionType == 0) {
                feeRate = transactionAmount < 50000000 ? 0.006 : transactionAmount < 200000000 ? 0.005 : transactionAmount < 900000000 ? 0.004 : transactionAmount < 1200000000 ? 0.005 : transactionAmount < 1500000000 ? 0.006 : 0.007;
            } else {
                feeRate = transactionAmount < 100000000 ? 0.004 : (transactionType == 1 ? 0.003 : 0.004);
            }
        } else if (prpType == 1) {
            feeRate = prpExclArea <= 85 ? (transactionType == 0 ? 0.005 : 0.004) : 0.009;
        } else {
            feeRate = 0.009;
        }
        return feeRate;
    }

    // MAXFEE 설정
    private double determineMaxFee(double transactionAmount, int prpType, int transactionType) {
        double maxFee = 0.0;
        if (prpType == 0 && transactionType == 0) {
            maxFee = transactionAmount < 50000000 ? 250000 : transactionAmount < 200000000 ? 800000 : 0;
        } else if (prpType == 0 && (transactionType == 1 || transactionType == 2)) {
            maxFee = transactionAmount < 50000000 ? 200000 : transactionAmount < 100000000 ? 300000 : 0;
        }
        return maxFee;
    }

    // 중개수수료 계산
    public double calculateBrokerageFee(double transactionAmount, double feeRate, double maxFee) {
        double calculatedFee = transactionAmount * feeRate;
        return (maxFee > 0 && calculatedFee > maxFee) ? maxFee : calculatedFee;
    }

    // 부가세 계산
    public double calculateVAT(double brokerageFee) {
        return brokerageFee * 0.1; // 부가세 10%
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
    
 // 사용자 ID를 기준으로 경매 및 매물 정보를 가져오는 메소드
    public List<AuctionAgentDTO> getAuctionsWithPropertyByUserPk(int userPk) {
        return AuctionDAO.getAuctionsWithPropertyByUserPk(userPk);
    }

}
