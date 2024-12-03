package com.lx.finalproject.dto.winning;


public class WinningDTO {
	
    private Integer bidPk;
    private Integer bidAmount;
    private String bidTime;
    private Integer auctionPk;
    private Integer agentPk;
    private String prpName;
    private Integer prpPrice;

    public String getPrpName() {
		return prpName;
	}

	public void setPrpName(String prpName) {
		this.prpName = prpName;
	}

	public Integer getPrpPrice() {
		return prpPrice;
	}

	public void setPrpPrice(Integer prpPrice) {
		this.prpPrice = prpPrice;
	}

	// Getters and Setters
    public Integer getBidPk() {
        return bidPk;
    }

    public void setBidPk(Integer bidPk) {
        this.bidPk = bidPk;
    }

    public Integer getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Integer bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public Integer getAuctionPk() {
        return auctionPk;
    }

    public void setAuctionPk(Integer auctionPk) {
        this.auctionPk = auctionPk;
    }

    public Integer getAgentPk() {
        return agentPk;
    }

    public void setAgentPk(Integer agentPk) {
        this.agentPk = agentPk;
    }

	@Override
	public String toString() {
		return "BidDTO [bidPk=" + bidPk + ", bidAmount=" + bidAmount + ", bidTime=" + bidTime + ", auctionPk="
				+ auctionPk + ", agentPk=" + agentPk + ", prpName=" + prpName + ", prpPrice=" + prpPrice
				+ ", getPrpName()=" + getPrpName() + ", getPrpPrice()=" + getPrpPrice() + ", getBidPk()=" + getBidPk()
				+ ", getBidAmount()=" + getBidAmount() + ", getBidTime()=" + getBidTime() + ", getAuctionPk()="
				+ getAuctionPk() + ", getAgentPk()=" + getAgentPk() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}
