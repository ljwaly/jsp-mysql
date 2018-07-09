package com.ljw.exception;



/**
 * 异常基类
 * @author PC
 *
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9207322021256300923L;

	private String errorCode;

	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public BaseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BaseException(String errorCode, String msg, Throwable cause) {
		super(msg, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
