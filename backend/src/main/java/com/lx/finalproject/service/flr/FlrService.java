package com.lx.finalproject.service.flr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lx.finalproject.dao.chat.ChatDAO;
import com.lx.finalproject.dao.flr.FlrDAO;
import com.lx.finalproject.dto.chat.ChatMessageDTO;
import com.lx.finalproject.dto.flr.FlrDTO;

import java.util.List;

@Service
public class FlrService {

	@Autowired
    private FlrDAO flrDao;

	@Transactional // 트랜잭션 적용
    public int saveFlrData(FlrDTO flrDto) {
        flrDao.insertFlrData(flrDto);
        if (flrDto.getFlrPk() == null) {
            throw new RuntimeException("Failed to retrieve generated flrPk.");
        }
        return flrDto.getFlrPk(); // flrPk가 제대로 설정되었는지 확인
    }
	
	// flrPk를 기반으로 recPk가 NULL인지 확인하는 메서드
    public boolean isRecPkNull(int flrPk) {
        Integer recPk = flrDao.checkRecPkByFlrPk(flrPk);
        return recPk == null; // NULL이면 true 반환
    }
}

