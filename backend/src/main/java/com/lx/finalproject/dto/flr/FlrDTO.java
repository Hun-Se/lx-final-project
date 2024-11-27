package com.lx.finalproject.dto.flr;

public class FlrDTO {
    private Integer flrPk;
    private Integer userPk;
    private Integer prpPk;
    private Integer chatPk;
    private Integer recPk;
    private String flrCateUpper;
    private String flrCateMiddle;
    private String flrCateLower;
    private String flrContent;
    private String flrDatetime;

    public String getFlrDatetime() {
		return flrDatetime;
	}

	public void setFlrDatetime(String flrDatetime) {
		this.flrDatetime = flrDatetime;
	}

	// Getters and Setters
    public Integer getFlrPk() {
        return flrPk;
    }

    public void setFlrPk(Integer flrPk) {
        this.flrPk = flrPk;
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

    public Integer getChatPk() {
        return chatPk;
    }

    public void setChatPk(Integer chatPk) {
        this.chatPk = chatPk;
    }

    public Integer getRecPk() {
        return recPk;
    }

    public void setRecPk(Integer recPk) {
        this.recPk = recPk;
    }

    public String getFlrCateUpper() {
        return flrCateUpper;
    }

    public void setFlrCateUpper(String flrCateUpper) {
        this.flrCateUpper = flrCateUpper;
    }

    public String getFlrCateMiddle() {
        return flrCateMiddle;
    }

    public void setFlrCateMiddle(String flrCateMiddle) {
        this.flrCateMiddle = flrCateMiddle;
    }

    public String getFlrCateLower() {
        return flrCateLower;
    }

    public void setFlrCateLower(String flrCateLower) {
        this.flrCateLower = flrCateLower;
    }

    public String getFlrContent() {
        return flrContent;
    }

    public void setFlrContent(String flrContent) {
        this.flrContent = flrContent;
    }
}