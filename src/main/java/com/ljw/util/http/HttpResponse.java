package com.ljw.util.http;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.StatusLine;

public class HttpResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1467653246169459012L;
	
	
	private Header[] headers;
	
	private long contentLength;
	
	private StatusLine statusLine;
	
    private String responseBody;
    
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public StatusLine getStatusLine() {
		return statusLine;
	}
	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}
	public String getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
	@Override
	public String toString() {
		return "HttpResponse [headers=" + Arrays.toString(headers) + ", contentLength=" + contentLength
				+ ", statusLine=" + statusLine + ", responseBody=" + responseBody + "]";
	}
    
}
