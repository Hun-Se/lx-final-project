package com.lx.finalproject.vo;

public class ChatmesVO {
	private Integer chatmesPk;
    private String chatmesDatetime;
    private String chatmesContent;
    private Integer userPkSender;
    private Integer agentPkSender;
    private Integer chatPk;
    
	public int getChatmesPk() {
		return chatmesPk;
	}
	public void setChatmesPk(int chatmesPk) {
		this.chatmesPk = chatmesPk;
	}
	public String getChatmesDatetime() {
		return chatmesDatetime;
	}
	public void setChatmesDatetime(String chatmesDatetime) {
		this.chatmesDatetime = chatmesDatetime;
	}
	public String getChatmesContent() {
		return chatmesContent;
	}
	public void setChatmesContent(String chatmesContent) {
		this.chatmesContent = chatmesContent;
	}
	public Integer getUserPkSender() {
		return userPkSender;
	}
	public void setUserPkSender(Integer userPkSender) {
		this.userPkSender = userPkSender;
	}
	public Integer getAgentPkSender() {
		return agentPkSender;
	}
	public void setAgentPkSender(Integer agentPkSender) {
		this.agentPkSender = agentPkSender;
	}
	public int getChatPk() {
		return chatPk;
	}
	public void setChatPk(int chatPk) {
		this.chatPk = chatPk;
	}
	
	@Override
	public String toString() {
		return "ChatmesVO [chatmesPk=" + chatmesPk + ", chatmesDatetime=" + chatmesDatetime + ", chatmesContent="
				+ chatmesContent + ", userPkSender=" + userPkSender + ", agentPkSender=" + agentPkSender + ", chatPk="
				+ chatPk + "]";
	}
	
}