package com.lx.finalproject.dto.prp;

import org.locationtech.jts.geom.Point;

public class PrpDTO {
	private String name;
	private Double price;
	private int coordPk;
	private Point location;
	private String latitude;
	private String longitude;
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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
	public int getCoordPk() {
		return coordPk;
	}
	public void setCoordPk(int coordPk) {
		this.coordPk = coordPk;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}

}
