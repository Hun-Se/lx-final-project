package com.lx.finalproject.vo;

public class RecVO {
	
	private Integer recPk;
    private String recDatetime;
    private String recFile;
    private Integer userPk;
    private Integer prpPk;
    private Integer agentPk;
	public Integer getRecPk() {
		return recPk;
	}
	public void setRecPk(Integer recPk) {
		this.recPk = recPk;
	}
	public String getRecDatetime() {
		return recDatetime;
	}
	public void setRecDatetime(String recDatetime) {
		this.recDatetime = recDatetime;
	}
	public String getRecFile() {
		return recFile;
	}
	public void setRecFile(String recFile) {
		this.recFile = recFile;
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
		return "RecVO [recPk=" + recPk + ", recDatetime=" + recDatetime + ", recFile=" + recFile + ", userPk=" + userPk
				+ ", prpPk=" + prpPk + ", agentPk=" + agentPk + "]";
	}
    
}