package com.lx.finalproject.controller.report;

import java.io.IOException;

import com.lx.finalproject.dto.report.ReportPrpInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import com.lx.finalproject.dto.report.ReportDTO;
import com.lx.finalproject.service.report.ReportService;
import com.lx.finalproject.vo.FlrVO;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public List<FlrVO> getReports() {
    	List<FlrVO> reports = reportService.getAllReports();
        System.out.println("Reports: " + reports); // 콘솔에서 데이터 확인
        return reports;
    }
    
    // 상세 정보 가져오기
    @GetMapping("/detail/{flrPk}")
    public FlrVO getReportDetail(@PathVariable("flrPk") int flrPk) {
        return reportService.getReportDetail(flrPk);
    }
    
    @GetMapping("/agent-details/{flrPk}")
    public ResponseEntity<ReportDTO> getAgentDetails(@PathVariable("flrPk") int flrPk) {
        ReportDTO agentDetails = reportService.getAgentDetailsByFlrPk(flrPk);
        return ResponseEntity.ok(agentDetails);
    }

    @GetMapping("/prpInfo/{prpPk}")
    public ReportPrpInfoDTO getReportPrpInfoByPrpPk(@PathVariable("prpPk") int prpPk) {
        ReportPrpInfoDTO prpInfo = reportService.getReportPrpInfoByPrpPk(prpPk);
        return prpInfo;
    }
}
