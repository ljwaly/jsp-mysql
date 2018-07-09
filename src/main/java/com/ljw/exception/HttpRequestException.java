package com.ljw.exception;

public class HttpRequestException extends BaseException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1928357039370595587L;

	
	
	public HttpRequestException(String msg) {
		super(msg);
	}

	public HttpRequestException(String errorCode, String msg) {
		super(errorCode, msg);
	}

	public HttpRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public HttpRequestException(String errorCode, String msg, Throwable cause) {
		super(errorCode, msg, cause);
	}


}
