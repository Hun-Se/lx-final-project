package com.lx.finalproject.service.reportprp;


import com.lx.finalproject.dao.reportprp.ReportPrpDAO;
import com.lx.finalproject.dto.reportprp.ReportPrpDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportPrpService {

    private final ReportPrpDAO reportPrpDAO;
    public ReportPrpService(ReportPrpDAO reportPrpDAO) {
        this.reportPrpDAO = reportPrpDAO;
    }

    @Transactional
    public void saveReport(ReportPrpDTO reportPrpDTO) {
        Integer recPk = null; // 녹음 파일의 PK를 저장할 변수
        List<ReportPrpDTO.FileInfo> otherFiles = new ArrayList<>(); // 나머지 파일 저장 리스트

        // 1. 파일 분류 및 저장 (녹음 파일과 나머지 파일 분리)
        if (reportPrpDTO.getFiles() != null && !reportPrpDTO.getFiles().isEmpty()) {
            for (ReportPrpDTO.FileInfo file : reportPrpDTO.getFiles()) {
                if (isAudioFile(file.getName())) {
                    reportPrpDAO.insertRec(file); // 녹음 파일 저장
                    recPk = file.getRecPk(); // 생성된 recPk 저장
                    System.out.println("생성된 recPk: " + recPk);
                } else {
                    otherFiles.add(file); // 나머지 파일 리스트에 추가
                }
            }
        }

        // 2. 신고 데이터 저장 (flr 테이블)
        reportPrpDTO.setRecPk(recPk); // 녹음 파일 PK 설정
        int flrPk = reportPrpDAO.insertFlr(reportPrpDTO); // 신고 데이터 저장 및 flrPk 반환

        // 3. 나머지 파일 정보 저장 (flr_files 테이블)
        if (!otherFiles.isEmpty()) {
            reportPrpDAO.insertFiles(flrPk, otherFiles);
        }
    }

    private boolean isAudioFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        return List.of("mp3", "wav", "m4a").contains(extension);
    }

}
