package com.lx.finalproject.dao;

import java.util.List;

import com.lx.finalproject.vo.ExampleVo;

public interface ExampleDao {
	
	public List<ExampleVo> getAllExamples();

	public ExampleVo getExampleById();

	public ExampleVo createExample();

	public ExampleVo updateExample();

	public void deleteExample();
}