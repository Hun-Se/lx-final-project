package com.lx.finalproject.controller.reportprp;

import com.lx.finalproject.dto.reportprp.ReportPrpDTO;
import com.lx.finalproject.service.reportprp.ReportPrpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reportPrp")
public class ReportPrpContoller {

    private final ReportPrpService reportService;

    public ReportPrpContoller(ReportPrpService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<String> submitReport(@RequestBody ReportPrpDTO reportDTO) {
        try {
            reportService.saveReport(reportDTO);
            return ResponseEntity.ok("신고 접수가 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("신고 접수 중 오류가 발생했습니다.");
        }
    }
}
