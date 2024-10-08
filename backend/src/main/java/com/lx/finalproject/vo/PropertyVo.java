package com.lx.finalproject.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyVo {
    private Long prp_pk;
    
    private String name;
    
    private Double price;
    
    private Double latitude;
    
    private Double longitude;

	public Long getPrp_pk() {
		return prp_pk;
	}

	public void setPrp_pk(Long prp_pk) {
		this.prp_pk = prp_pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
    
    
}