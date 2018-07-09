package com.ljw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 直接映射jsp页面，不进行前后缀适配
 * 
 * @author PC
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	public String goToIndex(){
		return "forward:/index.jsp";//直接映射jsp页面，不进行前后缀适配
	}




	@RequestMapping("/pic")
	public String goToIndexPoi(){
		return "forward:/100.jpg";//直接映射jsp页面，不进行前后缀适配
	}
}
