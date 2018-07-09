package com.ljw.exception;

public class FileEmptyException extends BaseException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1928357039370595587L;

	
	
	public FileEmptyException(String msg) {
		super(msg);
	}

	public FileEmptyException(String errorCode, String msg) {
		super(errorCode, msg);
	}

	public FileEmptyException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public FileEmptyException(String errorCode, String msg, Throwable cause) {
		super(errorCode, msg, cause);
	}

}
