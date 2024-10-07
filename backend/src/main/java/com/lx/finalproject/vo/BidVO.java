package com.lx.finalproject.vo;

public class BidVO {
    private Integer bidPk;
    private Integer bidAmount;
    private String bidTime;
    private Integer auctionPk;
    
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
	
	// ToString
	@Override
	public String toString() {
		return "BidVO [bidPk=" + bidPk + ", bidAmount=" + bidAmount + ", bidTime=" + bidTime + ", auctionPk="
				+ auctionPk + "]";
	}

}