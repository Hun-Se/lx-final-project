package com.lx.finalproject.vo;
import java.time.LocalDate;

public class RecVO {
	
	private Integer recPk;
    private LocalDate recDatetime;
    private String recFile;
    private Integer flrPk;
    private Integer userPk;
    private Integer prpPk;
    private Integer agentPk;
    
	public Integer getRecPk() {
		return recPk;
	}
	public void setRecPk(Integer recPk) {
		this.recPk = recPk;
	}
	public LocalDate getRecDatetime() {
		return recDatetime;
	}
	public void setRecDatetime(LocalDate recDatetime) {
		this.recDatetime = recDatetime;
	}
	public String getRecFile() {
		return recFile;
	}
	public void setRecFile(String recFile) {
		this.recFile = recFile;
	}
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
	public Integer getAgentPk() {
		return agentPk;
	}
	public void setAgentPk(Integer agentPk) {
		this.agentPk = agentPk;
	}
	
	@Override
	public String toString() {
        return "RecVO [recPk=" + recPk + ", recDatetime=" + recDatetime + ", recFile=" + recFile + ", flrPk=" + flrPk + "]";
    }
    
}