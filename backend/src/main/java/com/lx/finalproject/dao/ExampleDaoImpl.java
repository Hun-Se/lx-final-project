package com.lx.finalproject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lx.finalproject.vo.ExampleVo;

@Repository
public class ExampleDaoImpl implements ExampleDao {
	
	@Autowired
	private SqlSession sqlsession;
	
	@Override
	public List<ExampleVo> getAllExamples() {
		return sqlsession.selectList("getAllExamples");
	}

	@Override
	public ExampleVo getExampleById() {
		return null;
	}

	@Override
	public ExampleVo createExample() {
		return null;
	}
	
	@Override
	public ExampleVo updateExample() {
		return null;
	}

	
	@Override
	public void deleteExample() {
		
	}

}
