package com.lx.finalproject.dao.report;

import com.lx.finalproject.dto.report.ReportDTO;
import com.lx.finalproject.vo.FlrVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportDAO {
	List<FlrVO> getAllReports(); // 모든 신고 데이터를 가져오기
	
	FlrVO getReportDetail(@Param("flrPk") int flrPk);
	
	ReportDTO getAgentDetailsByFlrPk(int flrPk);
}
