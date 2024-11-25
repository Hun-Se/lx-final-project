package com.lx.finalproject.vo;

public class RealcostVO {

	private String real_cost;
	private String geom_wgs84; // ST_AsGeoJSON의 결과를 String으로 매핑
	 
	public String getReal_cost() {
		return real_cost;
	}
	
	public void setReal_cost(String real_cost) {
		this.real_cost = real_cost;
	}
	
	public String getGeom_wgs84() {
		return geom_wgs84;
	}
	
	public void setGeom_wgs84(String geom_wgs84) {
		this.geom_wgs84 = geom_wgs84;
	}
	
}




