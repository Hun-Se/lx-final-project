package com.lx.finalproject.dto.bid;


// 경매-중개수수료 계산
public class BidDTO {
	
    private Integer bidPk;
    private Integer bidAmount;
    private String bidTime;
    private Integer auctionPk;
    private Integer agentPk;

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
        return "BidDTO [bidPk=" + bidPk + ", bidAmount=" + bidAmount + ", bidTime=" + bidTime + ", auctionPk=" + auctionPk + ", agentPk=" + agentPk + "]";
    }
}
