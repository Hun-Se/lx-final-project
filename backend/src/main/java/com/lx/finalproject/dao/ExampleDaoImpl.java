package com.lx.finalproject.dao;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lx.finalproject.vo.ExampleVo;

@Repository
public class ExampleDaoImpl {
	
	@Autowired
	private SqlSession sqlsession;
	
	public List<ExampleVo> getAllExamples() {
	    List<ExampleVo> examples = sqlsession.selectList("getAllExamples");
	    return examples == null || examples.isEmpty() ? Collections.emptyList() : examples;
	}

	public ExampleVo getExampleById() {
		return null;
	}

	public ExampleVo createExample() {
		return null;
	}

	public ExampleVo createUser() {
		return null;
	}
	
	public ExampleVo updateExample() {
		return null;
	}

	public void deleteExample() {
		
	}

}
