package com.ljw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/file")
public class FileController {
	@RequestMapping("/upload")
	public ModelAndView fileModel(){
		
		ModelAndView modelAndView = new ModelAndView("forward:/fileupload/fileSend.jsp");
	
		return modelAndView;
	}
}
