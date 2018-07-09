package com.ljw.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ljw.threadpool.MessageThreadPool;


@RestController
@RequestMapping("pool")
public class PoolController {
	
	@Autowired
	private MessageThreadPool pool;
	
	@RequestMapping("queue")
	public Map<String, Object> testQueue(){
		Map<String, Object> map = new HashMap<String,Object>();
		for (int i = 0; i < 20; i++) {
			pool.addMessage(i+"");
		}
		
		return map;
	}
}
