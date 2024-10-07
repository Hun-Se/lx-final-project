package com.lx.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lx.finalproject.dto.PrpDetailsResponseDTO;
import com.lx.finalproject.dto.PrpFilterRequestDTO;
import com.lx.finalproject.service.PrpService;


@RestController
@RequestMapping("/api/prp")
public class PrpController {
	
	@Autowired
	private PrpService prpService;
	
	// 모든 매물을 보여주기
	@PostMapping
	public List<PrpDetailsResponseDTO> getPrpList(){
		System.out.println("요청받음");
		List<PrpDetailsResponseDTO> prpList = prpService.getPrpList();
		for (PrpDetailsResponseDTO item:prpList) {
			System.out.println(item.userPrint().toString());
		}
		System.out.println(prpList);
		
		/*for (PrpDetailsResponseDTO prp : prpList) {
			System.out.println("매물 ID:" + prp.getPrp().getPrpPk());
		}*/
		return prpList;
	}
	/*
	// 설정한 필터 값으로 해당하는 매물을 조회해서 리턴
	@PostMapping("/filter") 
    public List<PrpDetailsResponseDTO> getPrpListByFilter(@RequestBody PrpFilterRequestDTO filter) {
        
        List<PrpDetailsResponseDTO> prpFilterList = prpService.getPrpListByFilter(filter);
        return prpFilterList;
    } */
}