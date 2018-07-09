package com.ljw.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljw.exception.HttpRequestException;
import com.ljw.util.JsonUtil;
import com.ljw.util.http.HttpClientManager;
import com.ljw.util.http.HttpUtil;

/**
 * 发送远程请求测试
 * 
 * @author PC
 *
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private HttpClientManager httpClientManager;

	@Override
	public String getResult(String request) {

		// 请求url
		String url = "http://117.131.17.196/flow-service/province/code";

		// headers
		Map<String, String> headers = new HashMap<String, String>();

		// 请求参数
		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sourceIp", "112.60.255.0");
		NameValuePair[] params = HttpUtil.praseParameterMap(requestParams);

		try {
			String rtnJson = httpClientManager.httpGet(url, headers, null, params);
			return rtnJson;

		} catch (HttpRequestException e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public String postResult(String request) {
		// 请求url
		String url = "http://117.131.17.196/flow-service/province/code";

		// headers
		Map<String, String> headers = new HashMap<String, String>();
		//headers.put("Content-Type", "application/json");
		
		// 请求参数
		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("sourceIp", "112.60.255.0");
		NameValuePair[] params = HttpUtil.praseParameterMap(requestParams);

		try {
			String rtnJson = httpClientManager.httpPost(url, headers, null, params, null);
			return rtnJson;

		} catch (HttpRequestException e) {
			e.printStackTrace();
		}

		return "";
	}

	

	public String test121() {
		// 请求url
		String url = "http://117.131.17.196/flow-service//token/validate";

		// headers
		Map<String, String> headers = new HashMap<String, String>();
//		headers.put("Content-Type", "text/plain");
//		headers.put("Cookie", "JSESSIONID=0B24756D1B45C12E3D63E98C4657FAE4");
//		headers.put("User-Agent", "22");
		
		// 请求参数
		Map<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("token", "13113");
		NameValuePair[] params = HttpUtil.praseParameterMap(requestParams);

		try {
			String rtnJson = httpClientManager.httpGet(url, headers, null, params);
			return rtnJson;

		} catch (HttpRequestException e) {
			e.printStackTrace();
		}

		return "";
	}
}
