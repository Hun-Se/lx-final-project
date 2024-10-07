package com.lx.finalproject.vo;

public class BetSubstnVO {
    private Integer betsubstnPk;
    private String betsubstnTime;
    private Double betsubstnDist;
    private Integer substnPk;
    private Integer substnPk2;
    
    // Getters and Setters
	public Integer getBetsubstnPk() {
		return betsubstnPk;
	}
	public void setBetsubstnPk(Integer betsubstnPk) {
		this.betsubstnPk = betsubstnPk;
	}
	public String getBetsubstnTime() {
		return betsubstnTime;
	}
	public void setBetsubstnTime(String betsubstnTime) {
		this.betsubstnTime = betsubstnTime;
	}
	public Double getBetsubstnDist() {
		return betsubstnDist;
	}
	public void setBetsubstnDist(Double betsubstnDist) {
		this.betsubstnDist = betsubstnDist;
	}
	public Integer getSubstnPk() {
		return substnPk;
	}
	public void setSubstnPk(Integer substnPk) {
		this.substnPk = substnPk;
	}
	public Integer getSubstnPk2() {
		return substnPk2;
	}
	public void setSubstnPk2(Integer substnPk2) {
		this.substnPk2 = substnPk2;
	}
	
	// ToString
	@Override
	public String toString() {
		return "BetSubstnVO [betsubstnPk=" + betsubstnPk + ", betsubstnTime=" + betsubstnTime + ", betsubstnDist="
				+ betsubstnDist + ", substnPk=" + substnPk + ", substnPk2=" + substnPk2 + "]";
	}
	
}