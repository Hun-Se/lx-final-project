package com.lx.finalproject.dto.auction;


// 경매-중개수수료 계산
public class AuctionFeeDTO {
	
    private double brokerageFee;    // 중개수수료
    private double vat;             // 부가세
    private double totalFee;        // 총금액
    
    public AuctionFeeDTO(double brokerageFee, double vat) {
        this.brokerageFee = brokerageFee;
        this.vat = vat;
        this.totalFee = brokerageFee + vat;
    }
    
    // Getters and Setters
	public double getBrokerageFee() {
		return brokerageFee;
	}

	public void setBrokerageFee(double brokerageFee) {
		this.brokerageFee = brokerageFee;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}

	// ToString
	@Override
	public String toString() {
		return "AuctionFeeDTO [brokerageFee=" + brokerageFee + ", vat=" + vat + ", totalFee=" + totalFee + "]";
	}
    
}
