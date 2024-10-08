package com.lx.finalproject.vo;

import org.locationtech.jts.geom.Point;

public class CoordVO {
    private Integer coordPk;
    private Point location;
    
    // Getters and Setters
	public Integer getCoordPk() {
		return coordPk;
	}
	public void setCoordPk(Integer coordPk) {
		this.coordPk = coordPk;
	}
	public Point getLocation() {
        return location;
    }
    public void setLocation(Point location) {
        this.location = location;
    }
    
    // ToString
	@Override
	public String toString() {
		return "CoordVO [coordPk=" + coordPk + ", location=" + location + "]";
	}
	
}