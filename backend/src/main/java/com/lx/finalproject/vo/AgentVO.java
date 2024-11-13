package com.lx.finalproject.vo;

public class AgentVO {
    private Integer agentPk;
    private Integer userPk;
    private Integer agencyPk;
    
    // Getters and Setters
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
	public Integer getAgencyPk() {
		return agencyPk;
	}
	public void setAgencyPk(Integer agencyPk) {
		this.agencyPk = agencyPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "AgentVO [agentPk=" + agentPk + ", userPk=" + userPk + ", agencyPk="
				+ agencyPk + "]";
	}
	   
}