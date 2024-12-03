package com.lx.finalproject.dto.prp;

import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;

public class FavPrpDTO {
    private int favprpPk;
    private int userPk;
    private int prpPk;
    private String prpName;
    private String prpImg;
    private int prpType;
    private BigDecimal prpSuppArea;
    private BigDecimal prpExclArea;
    private String prpAddrDetail;
    private int prpPrice;

    public String getPrpImg() {
        return prpImg;
    }

    public void setPrpImg(String prpImg) {
        this.prpImg = prpImg;
    }

    public int getFavprpPk() {
        return favprpPk;
    }

    public void setFavprpPk(int favprpPk) {
        this.favprpPk = favprpPk;
    }

    public int getUserPk() {
        return userPk;
    }

    public void setUserPk(int userPk) {
        this.userPk = userPk;
    }

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

    public BigDecimal getPrpSuppArea() {
        return prpSuppArea;
    }

    public void setPrpSuppArea(BigDecimal prpSuppArea) {
        this.prpSuppArea = prpSuppArea;
    }

    public BigDecimal getPrpExclArea() {
        return prpExclArea;
    }

    public void setPrpExclArea(BigDecimal prpExclArea) {
        this.prpExclArea = prpExclArea;
    }

    public String getPrpAddrDetail() {
        return prpAddrDetail;
    }

    public void setPrpAddrDetail(String prpAddrDetail) {
        this.prpAddrDetail = prpAddrDetail;
    }

    public int getPrpPrice() {
        return prpPrice;
    }

    public void setPrpPrice(int prpPrice) {
        this.prpPrice = prpPrice;
    }

}
