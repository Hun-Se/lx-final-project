package com.lx.finalproject.service.report;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.report.ReportDAO;
import com.lx.finalproject.dto.report.ReportDTO;
import com.lx.finalproject.vo.FlrVO;

@Service
public class ReportService {
	
    @Autowired
    private ReportDAO reportDAO;

    // 신고 데이터 전체 조회
    public List<FlrVO> getAllReports() {
        return reportDAO.getAllReports();
    }
    
    public FlrVO getReportDetail(int flrPk) {
        return reportDAO.getReportDetail(flrPk);
    }
    
    public ReportDTO getAgentDetailsByFlrPk(int flrPk) {
        return reportDAO.getAgentDetailsByFlrPk(flrPk);
    }
}
