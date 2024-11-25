package com.lx.finalproject.dao.reportprp;

// FileReportDAO.java
import com.lx.finalproject.dto.reportprp.ReportPrpDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ReportPrpDAO {
    // 녹음 파일 저장 및 자동 생성된 PK 반환
    int insertRec(@Param("file") ReportPrpDTO.FileInfo file);

    // flr 테이블에 신고 접수 저장 및 자동 생성된 PK 반환
    int insertFlr(ReportPrpDTO reportDTO);
    // 나머지 파일 정보 저장
    void insertFiles(@Param("flrPk") int flrPk, @Param("files") List<ReportPrpDTO.FileInfo> files);
}

