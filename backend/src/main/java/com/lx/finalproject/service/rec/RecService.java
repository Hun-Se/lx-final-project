package com.lx.finalproject.service.rec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.chat.ChatDAO;
import com.lx.finalproject.dao.rec.RecDAO;
import com.lx.finalproject.dto.chat.ChatMessageDTO;
import com.lx.finalproject.vo.RecVO;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecService {

    @Autowired
    private RecDAO recDAO;

    public void saveRec(String filePath, int flrPk, int userPk, int prpPk, int agentPk) {
        RecVO rec = new RecVO();
        rec.setRecDatetime(LocalDate.now().toString());
        rec.setRecFile(filePath);
        rec.setFlrPk(flrPk);
        rec.setUserPk(userPk);
        rec.setPrpPk(prpPk);
        rec.setAgentPk(agentPk);

        recDAO.insertRec(rec);
    }
    
}
