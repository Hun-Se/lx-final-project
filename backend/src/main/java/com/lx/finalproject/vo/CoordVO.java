package com.lx.finalproject.vo;

import lombok.Data;

@Data
public class CoordVO {
    private Integer coordPk;    // 좌표 번호
    private Double coordLat;    // 위도
    private Double coordLong;   // 경도
}