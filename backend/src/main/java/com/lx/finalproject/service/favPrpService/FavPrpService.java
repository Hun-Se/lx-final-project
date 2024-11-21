package com.lx.finalproject.service.favPrpService;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.favprp.FavPrpDAO;
import com.lx.finalproject.vo.PrpVO;

@Service
public class FavPrpService {
	
    @Autowired
    private FavPrpDAO favPrpDAO;

    public List<PrpVO> getFavoritePropertiesByUserPk(int userPk) {
        List<PrpVO> favoriteProperties = favPrpDAO.getFavprpByUserPk(userPk);
        return (favoriteProperties != null) ? favoriteProperties : Collections.emptyList(); // nullPointerException Error 방지 로직입니다.
    }
}
