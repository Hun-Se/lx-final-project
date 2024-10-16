package com.lx.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.lx.finalproject.dto.PrpDetailsResponseDTO;
//import com.lx.finalproject.dto.PrpFilterRequestDTO;
//import com.lx.finalproject.service.PrpService;

@RestController
@RequestMapping("/api/properties")
public class PrpController {
   
	/*
	 * @GetMapping public String getPrpList(){ System.out.println("GET 요청받음");
	 * String prpList = "테스트"; return prpList; }
	 */
   @PostMapping
   public String postPrpList(){
      System.out.println("POST 요청받음");
      String prpList = "테스트";
      return prpList;
   }
}
   
   // 설정한 필터 값으로 해당하는 매물을 조회해서 리턴
