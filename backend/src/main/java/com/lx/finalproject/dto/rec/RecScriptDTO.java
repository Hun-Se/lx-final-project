package com.lx.finalproject.dto.rec;

public class RecScriptDTO {
    private Integer recscrPk;
    private String recscrContent;
    private String senderType; // 신고자 또는 피신고자 구분
    private boolean isProblematic;
    
	public Integer getRecscrPk() {
		return recscrPk;
	}
	public void setRecscrPk(Integer recscrPk) {
		this.recscrPk = recscrPk;
	}
	public String getRecscrContent() {
		return recscrContent;
	}
	public void setRecscrContent(String recscrContent) {
		this.recscrContent = recscrContent;
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