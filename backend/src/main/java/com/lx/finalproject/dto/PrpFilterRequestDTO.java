package com.lx.finalproject.dto;

import lombok.Data;

@Data
public class PrpFilterRequestDTO {
	
    private String propertyType;      // 거래 대상 (주택, 오피스텔 등)
    private String transactionType;   // 거래 유형 (매매, 전세, 월세 등)

    private Long salePriceMin;        // 매매 금액 (최소값)
    private Long salePriceMax;        // 매매 금액 (최대값)

    private Long jeonseMin;           // 전세 금액 (최소값)
    private Long jeonseMax;           // 전세 금액 (최대값)

    private Long depositMin;          // 보증금 (최소값)
    private Long depositMax;          // 보증금 (최대값)

    private Integer monthlyRentMin;   // 월세 금액 (최소값)
    private Integer monthlyRentMax;   // 월세 금액 (최대값)

    private String structureType;     // 구조 유형 (복층, 오픈방, 분리형 등)
    private String floor;        	  // 층수 유형 (옥탑방, 반지하, 지상층 등)

    private Double areaMin;           // 전용 면적 (최소값)
    private Double areaMax;           // 전용 면적 (최대값)
	 
}
