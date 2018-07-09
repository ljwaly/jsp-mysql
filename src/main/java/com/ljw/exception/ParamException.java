package com.ljw.exception;



/**
 * 参数异常
 * @author PC
 *
 */
public class ParamException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7404266865953018188L;

	public ParamException(String msg) {
		super(msg);
	}

	public ParamException(String errorCode, String msg) {
		super(errorCode, msg);
	}

	public ParamException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ParamException(String errorCode, String msg, Throwable cause) {
		super(errorCode, msg, cause);
	}

}
