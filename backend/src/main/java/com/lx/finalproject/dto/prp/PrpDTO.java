package com.lx.finalproject.dto.prp;

import java.math.BigDecimal;


public class PrpDTO {
    
	private Integer prpPk;  // 매물의 고유 ID
	private String name;
	private BigDecimal price;
	private int coordPk;
	private Double latitude;
	private Double longitude;
    private Integer prpTransType;

	public Integer getPrpTransType() {
		return prpTransType;
	}
	public void setPrpTransType(Integer prpTransType) {
		this.prpTransType = prpTransType;
	}
	public Integer getPrpPk() {
		return prpPk;
	}
	public void setPrpPk(Integer prpPk) {
		this.prpPk = prpPk;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 public BigDecimal getPrice() { return price; }
	    public void setPrice(BigDecimal price) { this.price = price; }
	public int getCoordPk() {
		return coordPk;
	}
	public void setCoordPk(int coordPk) {
		this.coordPk = coordPk;
	}


}
