package com.lx.finalproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.finalproject.dao.ExampleDaoImpl;
import com.lx.finalproject.vo.ExampleVo;

@Service
public class ExampleService {
	@Autowired
	private ExampleDaoImpl exampleDaoImpl;
	
	public List<ExampleVo> getAllExamples() {
		return exampleDaoImpl.getAllExamples();
	}

	public ExampleVo getExampleById(Long id) {
		return exampleDaoImpl.getExampleById();
	}

	public ExampleVo createExample(ExampleVo exampleVo) {
		return exampleDaoImpl.createExample();
	}

	public ExampleVo updateExample(Long id, ExampleVo exampleVo) {
		return exampleDaoImpl.updateExample();
	}

	public void deleteExample(Long id) {
		exampleDaoImpl.deleteExample();
	}
}
