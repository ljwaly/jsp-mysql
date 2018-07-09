package com.ljw.vo.reponse;

import java.io.Serializable;


/**
 * 控制器返回结果
 * @author PC
 *
 */
public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1667162084664489300L;
	
	private String resultDesc;
	private String resultCode;
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((resultCode == null) ? 0 : resultCode.hashCode());
		result = prime * result + ((resultDesc == null) ? 0 : resultDesc.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Response other = (Response) obj;
		if (resultCode == null) {
			if (other.resultCode != null)
				return false;
		} else if (!resultCode.equals(other.resultCode))
			return false;
		if (resultDesc == null) {
			if (other.resultDesc != null)
				return false;
		} else if (!resultDesc.equals(other.resultDesc))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reponse [resultDesc=" + resultDesc + ", resultCode=" + resultCode + "]";
	}
	public String getResultDesc() {
		return resultDesc;
	}
	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	
	
	public Response() {
		super();
	}
	
	public Response(String resultDesc, String resultCode) {
		super();
		this.resultDesc = resultDesc;
		this.resultCode = resultCode;
	}
	

}
