package com.lx.finalproject.vo;

public class AgencyVO {
    private Integer agencyPk;
    private String agencyImg;
    private String agencyName;
    private String agencyMobile;
    private String agencyLicense;
    private String agencyLocation;
    private Integer regionPk;
	public Integer getAgencyPk() {
		return agencyPk;
	}
	public void setAgencyPk(Integer agencyPk) {
		this.agencyPk = agencyPk;
	}
	public String getAgencyImg() {
		return agencyImg;
	}
	public void setAgencyImg(String agencyImg) {
		this.agencyImg = agencyImg;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyMobile() {
		return agencyMobile;
	}
	public void setAgencyMobile(String agencyMobile) {
		this.agencyMobile = agencyMobile;
	}
	public String getAgencyLicense() {
		return agencyLicense;
	}
	public void setAgencyLicense(String agencyLicense) {
		this.agencyLicense = agencyLicense;
	}
	public String getAgencyLocation() {
		return agencyLocation;
	}
	public void setAgencyLocation(String agencyLocation) {
		this.agencyLocation = agencyLocation;
	}
	public Integer getRegionPk() {
		return regionPk;
	}
	public void setRegionPk(Integer regionPk) {
		this.regionPk = regionPk;
	}
	
	@Override
	public String toString() {
		return "AgencyVO [agencyPk=" + agencyPk + ", agencyImg=" + agencyImg + ", agencyName=" + agencyName
				+ ", agencyMobile=" + agencyMobile + ", agencyLicense=" + agencyLicense + ", agencyLocation="
				+ agencyLocation + ", regionPk=" + regionPk + "]";
	}
    
}