package com.lx.finalproject.vo;

import java.text.DecimalFormat;

public class RealcostVO {
    private Double realCost; // 실거래가
    private String geom; // GeoJSON 형식의 지리 데이터 (EPSG:3857)

    // Getter 및 Setter
    public Double getRealCost() {
        return realCost;
    }

    public void setRealCost(String realCostStr) {
        // 쉼표를 제거하고 Double로 변환
        this.realCost = Double.parseDouble(realCostStr.replace(",", ""));
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom3857(String geom3857) {
        this.geom = geom!= null ? geom : "";
    }

    // 실거래가를 쉼표가 포함된 형식으로 변환하는 메서드
    private String formatRealCost(Double realCost) {
        if (realCost != null) {
            DecimalFormat formatter = new DecimalFormat("#,###");
            return formatter.format(realCost);
        }
        return "0";
    }
}
