package com.lx.finalproject.vo;

public class AuctionVO {
    private Integer auctionPk;
    private Integer auctionComm;
    private String auctionStart;
    private String auctionEnd;
    private Integer auctionStatus;
    private Integer agentPk;
    private Integer userPk;
    private Integer prpPk;
    private Integer inspReqPk;
    
    // Getters and Setters
	public Integer getAuctionPk() {
		return auctionPk;
	}
	public void setAuctionPk(Integer auctionPk) {
		this.auctionPk = auctionPk;
	}
	public Integer getAuctionComm() {
		return auctionComm;
	}
	public void setAuctionComm(Integer auctionComm) {
		this.auctionComm = auctionComm;
	}
	public String getAuctionStart() {
		return auctionStart;
	}
	public void setAuctionStart(String auctionStart) {
		this.auctionStart = auctionStart;
	}
	public String getAuctionEnd() {
		return auctionEnd;
	}
	public void setAuctionEnd(String auctionEnd) {
		this.auctionEnd = auctionEnd;
	}
	public Integer getAuctionStatus() {
		return auctionStatus;
	}
	public void setAuctionStatus(Integer auctionStatus) {
		this.auctionStatus = auctionStatus;
	}
	public Integer getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(Integer agentPk) {
		this.agentPk = agentPk;
	}
	public Integer getUserPk() {
		return userPk;
	}
	public void setUserPk(Integer userPk) {
		this.userPk = userPk;
	}
	public Integer getPrpPk() {
		return prpPk;
	}
	public void setPrpPk(Integer prpPk) {
		this.prpPk = prpPk;
	}
	public Integer getInspReqPk() {
		return inspReqPk;
	}
	public void setInspReqPk(Integer inspReqPk) {
		this.inspReqPk = inspReqPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "AuctionVO [auctionPk=" + auctionPk + ", auctionComm=" + auctionComm + ", auctionStart=" + auctionStart
				+ ", auctionEnd=" + auctionEnd + ", auctionStatus=" + auctionStatus + ", agentPk=" + agentPk
				+ ", userPk=" + userPk + ", prpPk=" + prpPk + ", inspReqPk=" + inspReqPk + "]";
	}

}

