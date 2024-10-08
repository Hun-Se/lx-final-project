package com.lx.finalproject.vo;

public class PrpVO {
    private Integer prpPk;
    private String prpName;
    private String prpImg;
    private Integer prpType;
    private Double prpSuppArea;
    private Double prpExclArea;
    private String prpBlock;
    private String prpFloor;
    private String prpUnit;
    private String prpAddrDetail;
    private Integer prpTransType;
    private Integer prpDeposit;
    private Integer prpPrice;
    private Integer prpMaintFee;
    private Integer prpParkingAvail;
    private String prpConstr;
    private String prpStrucType;
    private String prpDesc;
    private Integer prpStatus;
    private String prpRegTime;
    private Integer regionPk;
    private Integer userPk;
    private Integer coordPk;
    
    // Getters and Setters
	public Integer getPrpPk() {
		return prpPk;
	}
	public void setPrpPk(Integer prpPk) {
		this.prpPk = prpPk;
	}
	public String getPrpName() {
		return prpName;
	}
	public void setPrpName(String prpName) {
		this.prpName = prpName;
	}
	public String getPrpImg() {
		return prpImg;
	}
	public void setPrpImg(String prpImg) {
		this.prpImg = prpImg;
	}
	public Integer getPrpType() {
		return prpType;
	}
	public void setPrpType(Integer prpType) {
		this.prpType = prpType;
	}
	public Double getPrpSuppArea() {
		return prpSuppArea;
	}
	public void setPrpSuppArea(Double prpSuppArea) {
		this.prpSuppArea = prpSuppArea;
	}
	public Double getPrpExclArea() {
		return prpExclArea;
	}
	public void setPrpExclArea(Double prpExclArea) {
		this.prpExclArea = prpExclArea;
	}
	public String getPrpBlock() {
		return prpBlock;
	}
	public void setPrpBlock(String prpBlock) {
		this.prpBlock = prpBlock;
	}
	public String getPrpFloor() {
		return prpFloor;
	}
	public void setPrpFloor(String prpFloor) {
		this.prpFloor = prpFloor;
	}
	public String getPrpUnit() {
		return prpUnit;
	}
	public void setPrpUnit(String prpUnit) {
		this.prpUnit = prpUnit;
	}
	public String getPrpAddrDetail() {
		return prpAddrDetail;
	}
	public void setPrpAddrDetail(String prpAddrDetail) {
		this.prpAddrDetail = prpAddrDetail;
	}
	public Integer getPrpTransType() {
		return prpTransType;
	}
	public void setPrpTransType(Integer prpTransType) {
		this.prpTransType = prpTransType;
	}
	public Integer getPrpDeposit() {
		return prpDeposit;
	}
	public void setPrpDeposit(Integer prpDeposit) {
		this.prpDeposit = prpDeposit;
	}
	public Integer getPrpPrice() {
		return prpPrice;
	}
	public void setPrpPrice(Integer prpPrice) {
		this.prpPrice = prpPrice;
	}
	public Integer getPrpMaintFee() {
		return prpMaintFee;
	}
	public void setPrpMaintFee(Integer prpMaintFee) {
		this.prpMaintFee = prpMaintFee;
	}
	public Integer getPrpParkingAvail() {
		return prpParkingAvail;
	}
	public void setPrpParkingAvail(Integer prpParkingAvail) {
		this.prpParkingAvail = prpParkingAvail;
	}
	public String getPrpConstr() {
		return prpConstr;
	}
	public void setPrpConstr(String prpConstr) {
		this.prpConstr = prpConstr;
	}
	public String getPrpStrucType() {
		return prpStrucType;
	}
	public void setPrpStrucType(String prpStrucType) {
		this.prpStrucType = prpStrucType;
	}
	public String getPrpDesc() {
		return prpDesc;
	}
	public void setPrpDesc(String prpDesc) {
		this.prpDesc = prpDesc;
	}
	public Integer getPrpStatus() {
		return prpStatus;
	}
	public void setPrpStatus(Integer prpStatus) {
		this.prpStatus = prpStatus;
	}
	public String getPrpRegTime() {
		return prpRegTime;
	}
	public void setPrpRegTime(String prpRegTime) {
		this.prpRegTime = prpRegTime;
	}
	public Integer getRegionPk() {
		return regionPk;
	}
	public void setRegionPk(Integer regionPk) {
		this.regionPk = regionPk;
	}
	public Integer getUserPk() {
		return userPk;
	}
	public void setUserPk(Integer userPk) {
		this.userPk = userPk;
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
		return "PrpVO [prpPk=" + prpPk + ", prpName=" + prpName + ", prpImg=" + prpImg + ", prpType=" + prpType
				+ ", prpSuppArea=" + prpSuppArea + ", prpExclArea=" + prpExclArea + ", prpBlock=" + prpBlock
				+ ", prpFloor=" + prpFloor + ", prpUnit=" + prpUnit + ", prpAddrDetail=" + prpAddrDetail
				+ ", prpTransType=" + prpTransType + ", prpDeposit=" + prpDeposit + ", prpPrice=" + prpPrice
				+ ", prpMaintFee=" + prpMaintFee + ", prpParkingAvail=" + prpParkingAvail + ", prpConstr=" + prpConstr
				+ ", prpStrucType=" + prpStrucType + ", prpDesc=" + prpDesc + ", prpStatus=" + prpStatus
				+ ", prpRegTime=" + prpRegTime + ", regionPk=" + regionPk + ", userPk=" + userPk + ", coordPk="
				+ coordPk + "]";
	}
    
}