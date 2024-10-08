package com.lx.finalproject.vo;

public class FavPrpVO {
    private Integer favprpPk;
    private Integer userPk;
    private Integer prpPk;
    
    // Getters and Setters
	public Integer getFavprpPk() {
		return favprpPk;
	}
	public void setFavprpPk(Integer favprpPk) {
		this.favprpPk = favprpPk;
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
	
	// ToString
	@Override
	public String toString() {
		return "FavPrpVO [favprpPk=" + favprpPk + ", userPk=" + userPk + ", prpPk=" + prpPk + "]";
	}
    
}