package com.lx.finalproject.service.rec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.rec.RecDAO;
import com.lx.finalproject.dto.rec.RecScriptDTO;
import com.lx.finalproject.vo.AiRecDecsVO;
import com.lx.finalproject.vo.RecVO;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecService {

    @Autowired
    private RecDAO recDAO;

    public void saveRec(String filePath, int flrPk) {
        RecVO rec = new RecVO();
        rec.setRecDatetime(LocalDate.now());
        rec.setRecFile(filePath);
        rec.setFlrPk(flrPk);

        recDAO.insertRec(rec);
    }
    
    public List<RecScriptDTO> getRecScriptByFlrPk(int recPk) {
        return recDAO.findScriptByRecPk(recPk);
    }

    public Integer getRecPkByFlrPk(int flrPk) {
    	Integer recPk = recDAO.findRecPkByFlrPk(flrPk);
        System.out.println("Retrieved recPk for flrPk " + flrPk + ": " + recPk);
        return recPk;
    }

    public List<Integer> getProblematicMessagesByFlrPk(int RecPk) {
        return recDAO.findProblematicScriptByRecPk(RecPk);
    }
    
    public List<RecScriptDTO> getRecScriptByRecPkWithHighlights(int recPk) {
        List<RecScriptDTO> messages = recDAO.findScriptByRecPk(recPk); // 전체 메시지 가져오기
        List<Integer> problematicMessages = recDAO.findProblematicScriptByRecPk(recPk); // 문제 메시지 가져오기

        // 하이라이팅 처리
        for (RecScriptDTO message : messages) {
            if (problematicMessages.contains(message.getRecscrPk())) {
                message.setIsProblematic(true);
            }
        }
        return messages;
    }
    
    public AiRecDecsVO getAiRecDecsByRecPk(int recPk) {
        return recDAO.findAiRecDecsByRecPk(recPk);
    }
    
}
