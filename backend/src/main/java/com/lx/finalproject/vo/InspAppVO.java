package com.lx.finalproject.vo;

public class InspAppVO {
    private Integer inspappPk;
    private String inspappDate;
    private Integer inspreqPk;
    private Integer agentPk;
    
    // Getters and Setters
	public Integer getInspappPk() {
		return inspappPk;
	}
	public void setInspappPk(Integer inspappPk) {
		this.inspappPk = inspappPk;
	}
	public String getInspappDate() {
		return inspappDate;
	}
	public void setInspappDate(String inspappDate) {
		this.inspappDate = inspappDate;
	}
	public Integer getInspreqPk() {
		return inspreqPk;
	}
	public void setInspreqPk(Integer inspreqPk) {
		this.inspreqPk = inspreqPk;
	}
	public Integer getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(Integer agentPk) {
		this.agentPk = agentPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "InspAppVO [inspappPk=" + inspappPk + ", inspappDate=" + inspappDate + ", inspreqPk=" + inspreqPk
				+ ", agentPk=" + agentPk + "]";
	}
    
}