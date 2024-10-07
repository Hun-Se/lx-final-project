package com.lx.finalproject.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PrpVO {
    private Integer prpPk;               // 매물 번호
    private String prpName;              // 매물 이름
    private String prpImg;               // 매물 이미지 (테이블에 없으므로 필요 시 테이블에 추가)
    private Integer prpType;             // 매물 유형 (아파트, 오피스텔 등)
    private Double prpSuppArea;          // 공급 면적
    private Double prpExclArea;          // 전용 면적
    private String prpBlock;             // 블록
    private String prpFloor;             // 층
    private String prpUnit;              // 호수
    private String prpAddrDetail;        // 상세 주소
    private Integer prpTransType;        // 거래 유형 (매매, 전세, 월세 등)
    private Integer prpDeposit;          // 보증금
    private Integer prpPrice;            // 가격
    private Integer prpMaintFee;         // 관리비
    private Integer prpParkingAvail;     // 주차 가능 여부
    private String prpConstr;            // 건설사
    private String prpStrucType;         // 구조 유형 (복층, 오픈형 등)
    private String prpDesc;              // 설명
    private Integer prpStatus;           // 상태 (예약, 거래완료 등)
    private LocalDateTime prpRegTime;    // 등록 시간
    private Integer regionPk;            // 지역 외래키
    private Integer userPk;              // 사용자 외래키
    private Integer coordPk;             // 좌표 외래키
}
