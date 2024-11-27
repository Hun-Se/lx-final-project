package com.lx.finalproject.dao.rec;

import com.lx.finalproject.dto.rec.RecScriptDTO;
import com.lx.finalproject.vo.AiRecDecsVO;
import com.lx.finalproject.vo.RecVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RecDAO {

	void insertRec(RecVO rec);

	List<RecScriptDTO> findScriptByRecPk(@Param("recPk") int recPk);

	Integer findRecPkByFlrPk(@Param("flrPk") int flrPk);

	List<Integer> findProblematicScriptByRecPk(@Param("recPk") int recPk);
	
	AiRecDecsVO findAiRecDecsByRecPk(@Param("recPk") int recPk);
	
}