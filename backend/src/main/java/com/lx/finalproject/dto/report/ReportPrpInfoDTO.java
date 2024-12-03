package com.lx.finalproject.dto.report;

public class ReportPrpInfoDTO {
    private int prpPk;                // 매물 고유 키
    private String prpName;           // 매물 이름
    private int prpType;              // 매물 종류
    private double prpSuppArea;       // 공급 면적
    private int prpTransType;         // 거래 형태
    private double prpPrice;          // 매물 가격
    private double prpDeposit;        // 보증금
    private String prpAddrDetail;     // 상세 주소
    private String regionSiDo;        // 시/도
    private String regionSiGunGu;     // 시/군/구
    private String regionEupMyeonDong;// 읍/면/동
    private double latitude;          // 위도
    private double longitude;         // 경도

    // Getters and Setters
    public int getPrpPk() {
        return prpPk;
    }

    public void setPrpPk(int prpPk) {
        this.prpPk = prpPk;
    }

    public String getPrpName() {
        return prpName;
    }

    public void setPrpName(String prpName) {
        this.prpName = prpName;
    }

    public int getPrpType() {
        return prpType;
    }

    public void setPrpType(int prpType) {
        this.prpType = prpType;
    }

    public double getPrpSuppArea() {
        return prpSuppArea;
    }

    public void setPrpSuppArea(double prpSuppArea) {
        this.prpSuppArea = prpSuppArea;
    }

    public int getPrpTransType() {
        return prpTransType;
    }

    public void setPrpTransType(int prpTransType) {
        this.prpTransType = prpTransType;
    }

    public double getPrpPrice() {
        return prpPrice;
    }

    public void setPrpPrice(double prpPrice) {
        this.prpPrice = prpPrice;
    }

    public double getPrpDeposit() {
        return prpDeposit;
    }

    public void setPrpDeposit(double prpDeposit) {
        this.prpDeposit = prpDeposit;
    }

    public String getPrpAddrDetail() {
        return prpAddrDetail;
    }

    public void setPrpAddrDetail(String prpAddrDetail) {
        this.prpAddrDetail = prpAddrDetail;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
