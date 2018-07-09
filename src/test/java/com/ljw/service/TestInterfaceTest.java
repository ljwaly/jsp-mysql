package com.ljw.service;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.ljw.conf.http.HttpConf;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TestServiceImpl.class, HttpConf.class})
@TestPropertySource(locations = { "classpath:application.yml" })
public class TestInterfaceTest {

	
	@Autowired
	private TestServiceImpl testService;
	
	@Test
	public void haveATest(){
		
//		System.out.println("************************************************");
//		String getResult = testService.getResult("");
//		System.out.println("result:"+getResult);
//		System.out.println("************************************************");
//		System.out.println("************************************************");
//		String postResult = testService.postResult("");
//		System.out.println("result:"+postResult);
//		System.out.println("************************************************");
		String test121 = testService.test121();
		
		Map parseObject = JSON.parseObject(test121, Map.class);
		
		System.out.println(parseObject);
	}
}
