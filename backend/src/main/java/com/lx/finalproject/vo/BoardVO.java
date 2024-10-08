package com.lx.finalproject.vo;

public class BoardVO {
    private Integer boardPk;
    private String boardTitle;
    private String boardContent;
    private Integer userPk;
    private Integer regionPk;
    
    // Getters and Setters
	public Integer getBoardPk() {
		return boardPk;
	}
	public void setBoardPk(Integer boardPk) {
		this.boardPk = boardPk;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Integer getUserPk() {
		return userPk;
	}
	public void setUserPk(Integer userPk) {
		this.userPk = userPk;
	}
	public Integer getRegionPk() {
		return regionPk;
	}
	public void setRegionPk(Integer regionPk) {
		this.regionPk = regionPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "BoardVO [boardPk=" + boardPk + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", userPk=" + userPk + ", regionPk=" + regionPk + "]";
	}
	
}