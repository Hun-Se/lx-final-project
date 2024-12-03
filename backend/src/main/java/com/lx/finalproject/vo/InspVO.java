package com.lx.finalproject.vo;

public class InspVO {
    private Integer inspPk;
    private Integer inspappPk;
    
    // Getters and Setters
	public Integer getInspPk() {
		return inspPk;
	}
	public void setInspPk(Integer inspPk) {
		this.inspPk = inspPk;
	}
	public Integer getInspAppPk() {
		return inspappPk;
	}
	public void setInspAppPk(Integer inspappPk) {
		this.inspappPk = inspappPk;
	}
	
	// ToString
	@Override
	public String toString() {
		return "InspVO [inspPk=" + inspPk + ", inspAppPk=" + inspappPk + "]";
	}

}