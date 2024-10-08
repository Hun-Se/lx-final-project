package com.lx.finalproject.vo;


public class RegionVO {
    private Integer regionPk;
    private Integer regionCode;
    private String regionSiDo;
    private String regionSiGunGu;
    private String regionEupMyeonDong;
    private String regionRi;

    // Getters and Setters
    public Integer getRegionPk() {
		return regionPk;
	}
	public void setRegionPk(Integer regionPk) {
		this.regionPk = regionPk;
	}
	public Integer getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(Integer regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionSiDo() {
		return regionSiDo;
	}
	public void setRegionSiDo(String regionSiDo) {
		this.regionSiDo = regionSiDo;
	}
	public String getRegionSiGunGu() {
		return regionSiGunGu;
	}
	public void setRegionSiGunGu(String regionSiGunGu) {
		this.regionSiGunGu = regionSiGunGu;
	}
	public String getRegionEupMyeonDong() {
		return regionEupMyeonDong;
	}
	public void setRegionEupMyeonDong(String regionEupMyeonDong) {
		this.regionEupMyeonDong = regionEupMyeonDong;
	}
	public String getRegionRi() {
		return regionRi;
	}
	public void setRegionRi(String regionRi) {
		this.regionRi = regionRi;
	}
	
	// ToString
	@Override
	public String toString() {
		return "RegionVO [regionPk=" + regionPk + ", regionCode=" + regionCode + ", regionSiDo=" + regionSiDo
				+ ", regionSiGunGu=" + regionSiGunGu + ", regionEupMyeonDong=" + regionEupMyeonDong + ", regionRi="
				+ regionRi + "]";
	}
    
}