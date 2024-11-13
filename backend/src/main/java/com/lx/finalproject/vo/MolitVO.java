package com.lx.finalproject.vo;

public class MolitVO {
	
	private Integer molitPk;
    private String molitFlrDecstatus;
    private String molitFlrDeccontent;
    private Integer flrPk;
    
	public Integer getMolitPk() {
		return molitPk;
	}
	public void setMolitPk(Integer molitPk) {
		this.molitPk = molitPk;
	}
	public String getMolitFlrDecstatus() {
		return molitFlrDecstatus;
	}
	public void setMolitFlrDecstatus(String molitFlrDecstatus) {
		this.molitFlrDecstatus = molitFlrDecstatus;
	}
	public String getMolitFlrDeccontent() {
		return molitFlrDeccontent;
	}
	public void setMolitFlrDeccontent(String molitFlrDeccontent) {
		this.molitFlrDeccontent = molitFlrDeccontent;
	}
	public Integer getFlrPk() {
		return flrPk;
	}
	public void setFlrPk(Integer flrPk) {
		this.flrPk = flrPk;
	}
	
	@Override
	public String toString() {
		return "MolitVO [molitPk=" + molitPk + ", molitFlrDecstatus=" + molitFlrDecstatus + ", molitFlrDeccontent="
				+ molitFlrDeccontent + ", flrPk=" + flrPk + "]";
	}
    
}