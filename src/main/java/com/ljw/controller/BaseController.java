package com.ljw.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ljw.exception.FileEmptyException;
import com.ljw.exception.HttpRequestException;
import com.ljw.exception.ParamException;
import com.ljw.vo.reponse.Response;


/**
 * 控制器异常处理，捕获所有子类异常
 * @author PC
 *
 */
public class BaseController {
	
	
	public static final String ERROR_UNKOWN="ERROR_UNKOWN";
	
	

	@ExceptionHandler(Exception.class)
	public Response exceptionHandler(Exception e){
		Response response = new Response();
		response.setResultCode(BaseController.ERROR_UNKOWN);
		response.setResultDesc(e.getMessage());
		return response;
		
	}
	@ExceptionHandler(ParamException.class)
	public Response exceptionHandlerParamException(ParamException e){
		Response response = new Response();
		response.setResultCode(e.getErrorCode());
		response.setResultDesc(e.getMessage());
		return response;
		
	}
	@ExceptionHandler(FileEmptyException.class)
	public Response exceptionHandlerFileEmptyException(FileEmptyException e){
		Response response = new Response();
		response.setResultCode(e.getErrorCode());
		response.setResultDesc(e.getMessage());
		return response;
		
	}
	@ExceptionHandler(HttpRequestException.class)
	public Response exceptionHandlerHttpRequestException(HttpRequestException e){
		Response response = new Response();
		response.setResultCode(e.getErrorCode());
		response.setResultDesc(e.getMessage());
		return response;
		
	}
}
