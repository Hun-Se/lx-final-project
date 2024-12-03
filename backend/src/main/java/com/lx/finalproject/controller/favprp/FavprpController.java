package com.lx.finalproject.controller.favprp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lx.finalproject.service.favPrpService.FavPrpService;
import com.lx.finalproject.vo.FavPrpVO;
import com.lx.finalproject.vo.PrpVO;

@RestController
@RequestMapping("/api/favprp") // 엔드포인트 URL
public class FavprpController {

    @Autowired
    private FavPrpService favPrpService;

    // 관심 매물 리스트 조회
    @GetMapping("/{userPk}")
    public List<PrpVO> getFavoritePropertiesByUserPk(@PathVariable int userPk) {
        return favPrpService.getFavoritePropertiesByUserPk(userPk);
    }

    // 관심 매물 추가
    @PostMapping("/add")
    public String addFavprp(@RequestBody FavPrpVO favPrpVO) {
        favPrpService.addFavprp(favPrpVO);
        return "관심 매물 추가 성공";
    }

    // 관심 매물 삭제
    @DeleteMapping("/delete")
    public String deleteFavprp(@RequestBody FavPrpVO favPrpVO) {
        favPrpService.deleteFavprp(favPrpVO);
        return "관심 매물 삭제 성공";
    }
}