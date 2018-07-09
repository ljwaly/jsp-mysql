package com.ljw.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ljw.controller.BaseController;
import com.ljw.dao.EmpDao;
import com.ljw.dao.entity.Emp;

@RestController
public class MyController extends BaseController{

	@Autowired
	private EmpDao empDao;

	@RequestMapping("/mapper")
	public Map<String, Object> test() throws Exception{
		List<Emp> findAll = empDao.findAll();
		System.out.println(findAll);
		
		
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("result", findAll);
		return map;
		
	}
	
	@RequestMapping("/findByName")
	public Map<String, Object> test(@RequestParam(required = false, defaultValue="Joken") String ename) throws Exception{
		List<Emp> findAll = empDao.findByName(ename);
		System.out.println(findAll);
		
		
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("result", findAll);
		return map;
		
	}
	
	
}
