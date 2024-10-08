package com.lx.finalproject.vo;

public class CommentVO {
    private Integer commentPk;
    private String commentContent;
    private Integer boardPk;
    private Integer userPk;
    
    // Getters and Setters
	public Integer getCommentPk() {
		return commentPk;
	}
	public void setCommentPk(Integer commentPk) {
		this.commentPk = commentPk;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getBoardPk() {
		return boardPk;
	}
	public void setBoardPk(Integer boardPk) {
		this.boardPk = boardPk;
	}
	public Integer getUserPk() {
		return userPk;
	}
	public void setUserPk(Integer userPk) {
		this.userPk = userPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "CommentVO [commentPk=" + commentPk + ", commentContent=" + commentContent + ", boardPk=" + boardPk
				+ ", userPk=" + userPk + "]";
	}

}