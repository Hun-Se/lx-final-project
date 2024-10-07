package com.lx.finalproject.vo;

import lombok.Data;

@Data
public class RegionVO {
    private Integer regionPk;               // 지역 번호
    private Integer regionCode;             // 지역 코드
    private String regionSiDo;              // 시/도
    private String regionSiGunGu;           // 시/군/구
    private String regionEupMyeonDong;      // 읍/면/동
    private String regionRi;                // 리 (선택 사항)
}
