package com.lx.finalproject.dto.auction;

public class AuctionAgentDTO {
	
    private int auctionPk;
    private int auctionComm;
    private String auctionStart;
    private String auctionEnd;
    private int auctionStatus;
    private int agentPk;
    private int userPk;
    private int prpPk;
    private String prpName;
    private int prpTransType;
    private int prpPrice;
    private int inspReqPk;
    private String inspReqDate;

    // Getters and Setters
    public int getAuctionPk() {
        return auctionPk;
    }

    public void setAuctionPk(int auctionPk) {
        this.auctionPk = auctionPk;
    }

    public int getAuctionComm() {
        return auctionComm;
    }

    public void setAuctionComm(int auctionComm) {
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

    public int getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(int auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public int getAgentPk() {
        return agentPk;
    }

    public void setAgentPk(int agentPk) {
        this.agentPk = agentPk;
    }

    public int getUserPk() {
        return userPk;
    }

    public void setUserPk(int userPk) {
        this.userPk = userPk;
    }

    public int getPrpPk() {
        return prpPk;
    }

    public void setPrpPk(int prpPk) {
        this.prpPk = prpPk;
    }

    public String getPrpName() {
        return prpName;
    }

    public void setPrpName(String prpName) {
        this.prpName = prpName;
    }

    public int getPrpPrice() {
        return prpPrice;
    }

    public void setPrpPrice(int prpPrice) {
        this.prpPrice = prpPrice;
    }

	public int getInspReqPk() {
		return inspReqPk;
	}

	public void setInspReqPk(int inspReqPk) {
		this.inspReqPk = inspReqPk;
	}

	public String getInspReqDate() {
		return inspReqDate;
	}

	public void setInspReqDate(String inspReqDate) {
		this.inspReqDate = inspReqDate;
	}

	public int getPrpTransType() {
		return prpTransType;
	}

	public void setPrpTransType(int prpTransType) {
		this.prpTransType = prpTransType;
	}
    
    
}
