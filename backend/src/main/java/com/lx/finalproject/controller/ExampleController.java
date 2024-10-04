package com.lx.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.finalproject.service.ExampleService;
import com.lx.finalproject.vo.ExampleVo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/example")
public class ExampleController {
	
	@Autowired
	private ExampleService exampleService;
    
	// 전체 조회
	@GetMapping
    public List<ExampleVo> getAllExamples() {		
    	return exampleService.getAllExamples();
    }
	
	// id별 조회
	@GetMapping("/{id}")
    public ExampleVo getExampleById(@PathVariable Long id) {
        return exampleService.getExampleById(id);
    }
	
	// 등록
	@PostMapping
	public ExampleVo createExample(@RequestBody ExampleVo exampleVo) {
		return exampleService.createExample(exampleVo);
	}
	
	// 수정
	@PutMapping("/{id}")
	public ExampleVo updateExample(@PathVariable Long id, @RequestBody ExampleVo exampleVo) {
		return exampleService.updateExample(id, exampleVo);
	}
	
	
	// 삭제
	@DeleteMapping("/{id}")
	public void deleteExample(@PathVariable Long id) {
		exampleService.deleteExample(id);
	}
	
	// ex) /examples/1?name="김세훈" 과 같은 url로 요청을 받을때
	//	@GetMapping("/{id}")
	//	public Example getExampleByIdAndFilter(@PathVariable Long id, @RequestParam(required = false) String filter) {
	//	    return exampleService.getExample(id, filter);
	//	}
	
	
	// ex) /examples/1/detail 과 같은 url로 요청을 받을떄
	//	@GetMapping("/{id}/{type}")
	//	public Example getExampleByIdAndType(@PathVariable Long id, @PathVariable String type) {
	//	    return exampleService.getExampleByIdAndType(id, type);
	//	}
	
}