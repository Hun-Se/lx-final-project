package com.lx.finalproject.vo;

public class ChatAiVO {
	
	private Integer chataiPk;
    private String chataiFlrDecstatus;
    private String chataiFlrDecscontent;
    private Integer chatmesPk;
	public Integer getChataiPk() {
		return chataiPk;
	}
	public void setChataiPk(Integer chataiPk) {
		this.chataiPk = chataiPk;
	}
	public String getChataiFlrDecstatus() {
		return chataiFlrDecstatus;
	}
	public void setChataiFlrDecstatus(String chataiFlrDecstatus) {
		this.chataiFlrDecstatus = chataiFlrDecstatus;
	}
	public String getChataiFlrDecscontent() {
		return chataiFlrDecscontent;
	}
	public void setChataiFlrDecscontent(String chataiFlrDecscontent) {
		this.chataiFlrDecscontent = chataiFlrDecscontent;
	}
	public Integer getChatmesPk() {
		return chatmesPk;
	}
	public void setChatmesPk(Integer chatmesPk) {
		this.chatmesPk = chatmesPk;
	}
	
	@Override
	public String toString() {
		return "ChatAiVO [chataiPk=" + chataiPk + ", chataiFlrDecstatus=" + chataiFlrDecstatus
				+ ", chataiFlrDecscontent=" + chataiFlrDecscontent + ", chatmesPk=" + chatmesPk + "]";
	}
	
}