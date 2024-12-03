package com.lx.finalproject.service.favPrpService;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.favprp.FavPrpDAO;
import com.lx.finalproject.vo.FavPrpVO;
import com.lx.finalproject.vo.PrpVO;

@Service
public class FavPrpService {
	
    @Autowired
    private FavPrpDAO favPrpDAO;

    // 관심 매물 조회
    public List<PrpVO> getFavoritePropertiesByUserPk(int userPk) {
        System.out.println("Fetching favorite properties for userPk: " + userPk); // 디버깅용
        List<PrpVO> favoriteProperties = favPrpDAO.getFavprpByUserPk(userPk);
        System.out.println("Fetched properties: " + favoriteProperties); // 디버깅용
        return (favoriteProperties != null) ? favoriteProperties : Collections.emptyList();
    }

    // 관심 매물 추가
    public void addFavprp(FavPrpVO favPrpVO) {
        favPrpDAO.addFavprp(favPrpVO);
    }

    // 관심 매물 삭제
    public void deleteFavprp(FavPrpVO favPrpVO) {
        favPrpDAO.deleteFavprp(favPrpVO);
    }
}
