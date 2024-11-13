package com.lx.finalproject.vo;

public class ChatVO {
    private Integer chatPk;
    private Integer userPk;
    private Integer prpPk;
    private Integer agentPk;
    
	public Integer getChatPk() {
		return chatPk;
	}
	public void setChatPk(Integer chatPk) {
		this.chatPk = chatPk;
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
	public Integer getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(Integer agentPk) {
		this.agentPk = agentPk;
	}
	
	@Override
	public String toString() {
		return "ChatVO [chatPk=" + chatPk + ", userPk=" + userPk + ", prpPk=" + prpPk + ", agentPk=" + agentPk + "]";
	}
	
}