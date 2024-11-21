package com.lx.finalproject.vo;

public class PrpVO {
    private Integer prpPk; // 매물 고유 키
    private String prpName; // 중개대상물명
    private String prpImg; // 중개대상물 이미지
    private Integer prpType; // 중개대상물 종류
    private Double prpSuppArea; // 공급면적
    private Double prpExclArea; // 전용면적
    private Integer prpTransType; // 거래 형태
    private Double prpDeposit; // 보증금
    private Double prpPrice; // 가격
    private Double prpMaintFee; // 관리비
    private String prpConstr; // 건설사
    private String prpConstrYear; // 건축년도
    private String prpBlock; // 동
    private String prpFloor; // 층
    private String prpUnit; // 호
    private String prpAddrDetail; // 상세주소
    private Integer prpNumRooms; // 방 개수
    private Integer prpNumBaths; // 욕실 개수
    private String prpOri; // 방향
    private Integer prpParkingAvail; // 주차 가능 대수
    private Integer prpTotalBlock; // 총 동수
    private Integer prpTotalFloor; // 총 층수
    private String prpDesc; // 상세설명
    private String prpNearSchool; // 인근 학교 이름
    private String prpRegTime; // 등록 시간
    private Double prpNearSubDist; // 인근 역 거리
    private Double prpNearSchoolDist; // 인근 학교 거리

    private Integer regionPk; // 지역 코드
    private Integer userPk; // 사용자 코드
    private Integer agentPk; // 중개사 코드
    private Integer prpNearSub; // 인근 역 코드
    private Integer coordPk; // 좌표 코드
    
    private int predict2022;
    private int predict2023;
    private int predict2024;
    private int predict2025;

	// Getters and Setters
    public Integer getPrpPk() {
        return prpPk;
    }

    public void setPrpPk(Integer prpPk) {
        this.prpPk = prpPk;
    }

    public String getPrpName() {
        return prpName;
    }

    public void setPrpName(String prpName) {
        this.prpName = prpName;
    }

    public String getPrpImg() {
        return prpImg;
    }

    public void setPrpImg(String prpImg) {
        this.prpImg = prpImg;
    }

    public Integer getPrpType() {
        return prpType;
    }

    public void setPrpType(Integer prpType) {
        this.prpType = prpType;
    }

    public Double getPrpSuppArea() {
        return prpSuppArea;
    }

    public void setPrpSuppArea(Double prpSuppArea) {
        this.prpSuppArea = prpSuppArea;
    }

    public Double getPrpExclArea() {
        return prpExclArea;
    }

    public void setPrpExclArea(Double prpExclArea) {
        this.prpExclArea = prpExclArea;
    }

    public Integer getPrpTransType() {
        return prpTransType;
    }

    public void setPrpTransType(Integer prpTransType) {
        this.prpTransType = prpTransType;
    }

    public Double getPrpDeposit() {
        return prpDeposit;
    }

    public void setPrpDeposit(Double prpDeposit) {
        this.prpDeposit = prpDeposit;
    }

    public Double getPrpPrice() {
        return prpPrice;
    }

    public void setPrpPrice(Double prpPrice) {
        this.prpPrice = prpPrice;
    }

    public Double getPrpMaintFee() {
        return prpMaintFee;
    }

    public void setPrpMaintFee(Double prpMaintFee) {
        this.prpMaintFee = prpMaintFee;
    }

    public String getPrpConstr() {
        return prpConstr;
    }

    public void setPrpConstr(String prpConstr) {
        this.prpConstr = prpConstr;
    }

    public String getPrpConstrYear() {
        return prpConstrYear;
    }

    public void setPrpConstrYear(String prpConstrYear) {
        this.prpConstrYear = prpConstrYear;
    }

    public String getPrpBlock() {
        return prpBlock;
    }

    public void setPrpBlock(String prpBlock) {
        this.prpBlock = prpBlock;
    }

    public String getPrpFloor() {
        return prpFloor;
    }

    public void setPrpFloor(String prpFloor) {
        this.prpFloor = prpFloor;
    }

    public String getPrpUnit() {
        return prpUnit;
    }

    public void setPrpUnit(String prpUnit) {
        this.prpUnit = prpUnit;
    }

    public String getPrpAddrDetail() {
        return prpAddrDetail;
    }

    public void setPrpAddrDetail(String prpAddrDetail) {
        this.prpAddrDetail = prpAddrDetail;
    }

    public Integer getPrpNumRooms() {
        return prpNumRooms;
    }

    public void setPrpNumRooms(Integer prpNumRooms) {
        this.prpNumRooms = prpNumRooms;
    }

    public Integer getPrpNumBaths() {
        return prpNumBaths;
    }

    public void setPrpNumBaths(Integer prpNumBaths) {
        this.prpNumBaths = prpNumBaths;
    }

    public String getPrpOri() {
        return prpOri;
    }

    public void setPrpOri(String prpOri) {
        this.prpOri = prpOri;
    }

    public Integer getPrpParkingAvail() {
        return prpParkingAvail;
    }

    public void setPrpParkingAvail(Integer prpParkingAvail) {
        this.prpParkingAvail = prpParkingAvail;
    }

    public Integer getPrpTotalBlock() {
        return prpTotalBlock;
    }

    public void setPrpTotalBlock(Integer prpTotalBlock) {
        this.prpTotalBlock = prpTotalBlock;
    }

    public Integer getPrpTotalFloor() {
        return prpTotalFloor;
    }

    public void setPrpTotalFloor(Integer prpTotalFloor) {
        this.prpTotalFloor = prpTotalFloor;
    }

    public String getPrpDesc() {
        return prpDesc;
    }

    public void setPrpDesc(String prpDesc) {
        this.prpDesc = prpDesc;
    }

    public String getPrpNearSchool() {
        return prpNearSchool;
    }

    public void setPrpNearSchool(String prpNearSchool) {
        this.prpNearSchool = prpNearSchool;
    }

    public String getPrpRegTime() {
        return prpRegTime;
    }

    public void setPrpRegTime(String prpRegTime) {
        this.prpRegTime = prpRegTime;
    }

    public Double getPrpNearSubDist() {
        return prpNearSubDist;
    }

    public void setPrpNearSubDist(Double prpNearSubDist) {
        this.prpNearSubDist = prpNearSubDist;
    }

    public Double getPrpNearSchoolDist() {
        return prpNearSchoolDist;
    }

    public void setPrpNearSchoolDist(Double prpNearSchoolDist) {
        this.prpNearSchoolDist = prpNearSchoolDist;
    }

    public Integer getRegionPk() {
        return regionPk;
    }

    public void setRegionPk(Integer regionPk) {
        this.regionPk = regionPk;
    }

    public Integer getUserPk() {
        return userPk;
    }

    public void setUserPk(Integer userPk) {
        this.userPk = userPk;
    }

    public Integer getAgentPk() {
        return agentPk;
    }

    public void setAgentPk(Integer agentPk) {
        this.agentPk = agentPk;
    }

    public Integer getPrpNearSub() {
        return prpNearSub;
    }

    public void setPrpNearSub(Integer prpNearSub) {
        this.prpNearSub = prpNearSub;
    }

    public Integer getCoordPk() {
        return coordPk;
    }

    public void setCoordPk(Integer coordPk) {
        this.coordPk = coordPk;
    }
    
    public int getPredict2022() {
		return predict2022;
	}

	public void setPredict2022(int predict2022) {
		this.predict2022 = predict2022;
	}

	public int getPredict2023() {
		return predict2023;
	}

	public void setPredict2023(int predict2023) {
		this.predict2023 = predict2023;
	}

	public int getPredict2024() {
		return predict2024;
	}

	public void setPredict2024(int predict2024) {
		this.predict2024 = predict2024;
	}

	public int getPredict2025() {
		return predict2025;
	}

	public void setPredict2025(int predict2025) {
		this.predict2025 = predict2025;
	}

    // ToString
    @Override
    public String toString() {
        return "PrpVO{" +
                "prpPk=" + prpPk +
                ", prpName='" + prpName + '\'' +
                ", prpImg='" + prpImg + '\'' +
                ", prpType=" + prpType +
                ", prpSuppArea=" + prpSuppArea +
                ", prpExclArea=" + prpExclArea +
                ", prpTransType=" + prpTransType +
                ", prpDeposit=" + prpDeposit +
                ", prpPrice=" + prpPrice +
                ", prpMaintFee=" + prpMaintFee +
                ", prpConstr='" + prpConstr + '\'' +
                ", prpConstrYear='" + prpConstrYear + '\'' +
                ", prpBlock='" + prpBlock + '\'' +
                ", prpFloor='" + prpFloor + '\'' +
                ", prpUnit='" + prpUnit + '\'' +
                ", prpAddrDetail='" + prpAddrDetail + '\'' +
                ", prpNumRooms=" + prpNumRooms +
                ", prpNumBaths=" + prpNumBaths +
                ", prpOri='" + prpOri + '\'' +
                ", prpParkingAvail=" + prpParkingAvail +
                ", prpTotalBlock=" + prpTotalBlock +
                ", prpTotalFloor=" + prpTotalFloor +
                ", prpDesc='" + prpDesc + '\'' +
                ", prpNearSchool='" + prpNearSchool + '\'' +
                ", prpRegTime='" + prpRegTime + '\'' +
                ", prpNearSubDist=" + prpNearSubDist +
                ", prpNearSchoolDist=" + prpNearSchoolDist +
                ", regionPk=" + regionPk +
                ", userPk=" + userPk +
                ", agentPk=" + agentPk +
                ", prpNearSub=" + prpNearSub +
                ", coordPk=" + coordPk +
                '}';
    }
}
