package com.lx.finalproject.controller.favprp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lx.finalproject.service.favPrpService.FavPrpService;
import com.lx.finalproject.vo.PrpVO;

@RestController
@RequestMapping("/api/favprp") // 엔드포인트 URL
public class FavprpController {

    @Autowired
    private FavPrpService favPrpService;

    // 관심 매물 리스트 조회
    @GetMapping("/{userPk}")
    public List<PrpVO> getFavoritePropertiesByUserPk(@PathVariable int userPk) {
        // Service 메서드에 userPk를 전달해 조회
        return favPrpService.getFavoritePropertiesByUserPk(userPk);
    }
}