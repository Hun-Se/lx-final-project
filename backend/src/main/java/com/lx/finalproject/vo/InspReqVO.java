package com.lx.finalproject.vo;

public class InspReqVO {
    private Integer inspreqPk;
    private String inspreqDate;
    private Integer userPk;
    private Integer prpPk;
    
    // Getters and Setters
	public Integer getInspreqPk() {
		return inspreqPk;
	}
	public void setInspreqPk(Integer inspreqPk) {
		this.inspreqPk = inspreqPk;
	}
	public String getInspreqDate() {
		return inspreqDate;
	}
	public void setInspreqDate(String inspreqDate) {
		this.inspreqDate = inspreqDate;
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
	
	// ToString
	@Override
	public String toString() {
		return "InspReqVO [inspreqPk=" + inspreqPk + ", inspreqDate=" + inspreqDate + ", userPk=" + userPk + ", prpPk="
				+ prpPk + "]";
	}	
}