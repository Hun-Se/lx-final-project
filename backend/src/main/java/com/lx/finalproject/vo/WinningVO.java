package com.lx.finalproject.vo;

public class WinningVO {
    private Integer winningPk;
    private String winningTime;
    private Integer bidPk;
    
    // Getters and Setters
	public Integer getWinningPk() {
		return winningPk;
	}
	public void setWinningPk(Integer winningPk) {
		this.winningPk = winningPk;
	}
	public String getWinningTime() {
		return winningTime;
	}
	public void setWinningTime(String winningTime) {
		this.winningTime = winningTime;
	}
	public Integer getBidPk() {
		return bidPk;
	}
	public void setBidPk(Integer bidPk) {
		this.bidPk = bidPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "WinningVO [winningPk=" + winningPk + ", winningTime=" + winningTime + ", bidPk=" + bidPk + "]";
	}

}