package com.lx.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lx.finalproject.vo.CoordVO;
import com.lx.finalproject.vo.PrpVO;
import com.lx.finalproject.vo.RegionVO;
import com.lx.finalproject.vo.UserVO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrpDetailsResponseDTO {
    private UserVO user;          // 사용자 정보
    private RegionVO region;      // 지역 정보
    private CoordVO coord;        // 좌표 정보
    private PrpVO prp;            // 매물 정보
    
    public UserVO userPrint() {
    	return this.user;
    }
}