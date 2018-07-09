package com.ljw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 进行前后缀适配
 * @author PC
 *
 */
@Controller
public class MainController extends BaseController{

	
	
	
	@RequestMapping("/welcome")
	public String main(){
		System.out.println("welcome");
		return "welcome";
		
	}
	

}
