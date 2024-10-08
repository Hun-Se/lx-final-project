package com.lx.finalproject.vo;

public class SubstnVO {
    private Integer substnPk;
    private String substnLine;
    private String substnName;
    private Integer coordPk;
    
    // Getters and Setters
	public Integer getSubstnPk() {
		return substnPk;
	}
	public void setSubstnPk(Integer substnPk) {
		this.substnPk = substnPk;
	}
	public String getSubstnLine() {
		return substnLine;
	}
	public void setSubstnLine(String substnLine) {
		this.substnLine = substnLine;
	}
	public String getSubstnName() {
		return substnName;
	}
	public void setSubstnName(String substnName) {
		this.substnName = substnName;
	}
	public Integer getCoordPk() {
		return coordPk;
	}
	public void setCoordPk(Integer coordPk) {
		this.coordPk = coordPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "SubstnVO [substnPk=" + substnPk + ", substnLine=" + substnLine + ", substnName=" + substnName
				+ ", coordPk=" + coordPk + "]";
	}
	
}