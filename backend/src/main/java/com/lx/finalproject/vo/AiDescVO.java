package com.lx.finalproject.vo;

public class AiDescVO {
	
	private Integer aidecsPk;
    private String flrDecstatus;
    private String flrDecscontent;
	public Integer getAidecsPk() {
		return aidecsPk;
	}
	public void setAidecsPk(Integer aidecsPk) {
		this.aidecsPk = aidecsPk;
	}
	public String getFlrDecstatus() {
		return flrDecstatus;
	}
	public void setFlrDecstatus(String flrDecstatus) {
		this.flrDecstatus = flrDecstatus;
	}
	public String getFlrDecscontent() {
		return flrDecscontent;
	}
	public void setFlrDecscontent(String flrDecscontent) {
		this.flrDecscontent = flrDecscontent;
	}
	
	@Override
	public String toString() {
		return "AidescVO [aidecsPk=" + aidecsPk + ", flrDecstatus=" + flrDecstatus + ", flrDecscontent="
				+ flrDecscontent + "]";
	}
    
}