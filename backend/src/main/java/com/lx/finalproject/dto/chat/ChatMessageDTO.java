package com.lx.finalproject.dto.chat;

public class ChatMessageDTO {
    private Integer chatmesPk;
    private String chatmesDatetime;
    private String chatmesContent;
    private String senderType; // 신고자 또는 피신고자 구분
    private boolean isProblematic;
   
	public Integer getChatmesPk() {
		return chatmesPk;
	}
	public void setChatmesPk(Integer chatmesPk) {
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
	public String getSenderType() {
		return senderType;
	}
	public void setSenderType(String senderType) {
		this.senderType = senderType;
	}
	public boolean getIsProblematic() {
		return isProblematic;
	}
	public void setIsProblematic(boolean isProblematic) {
		this.isProblematic = isProblematic;
	}

    
}