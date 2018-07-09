package com.ljw.util.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.log4j.Logger;

import com.ljw.exception.HttpRequestException;

public class HttpUtil {

	private static Logger log = Logger.getLogger(HttpUtil.class);

	/**
	 * http get请求
	 * 
	 * @param url
	 *            请求url
	 * @param httpClient
	 *            httpClient
	 * @param headers
	 *            头信息
	 * @param methodParams
	 *            请求方法额外参数
	 * @param params
	 *            请求参数
	 * @return
	 * @throws HttpRequestException
	 */
	public static String httpGet(String url, HttpClient httpClient, Map<String, String> headers,
			Map<String, Object> methodParams, NameValuePair[] params) throws HttpRequestException {
		GetMethod getMethod = new GetMethod(url);
		setMethodHeader(getMethod, headers);
		setMethodParams(getMethod, methodParams);
		if (params != null) {
			getMethod.setQueryString(params);
		}
		HttpResponse httpResponse = getResponseStr(getMethod, httpClient);
		return httpResponse == null ? "" : httpResponse.getResponseBody();
	}

	/**
	 * http get请求
	 * 
	 * @param url
	 *            请求url
	 * @param httpClient
	 *            httpClient
	 * @param headers
	 *            头信息
	 * @param methodParams
	 *            请求方法额外参数
	 * @param queryStr
	 *            请求参数信息，需urlencode且不能以？开头
	 * @return
	 * @throws HttpRequestException
	 */
	public static String httpGet(String url, HttpClient httpClient, Map<String, String> headers,
			Map<String, Object> methodParams, String queryStr) throws HttpRequestException {
		GetMethod getMethod = new GetMethod(url);
		setMethodHeader(getMethod, headers);
		setMethodParams(getMethod, methodParams);
		if (queryStr != null && !"".equals(queryStr)) {
			getMethod.setQueryString(queryStr);
		}
		HttpResponse httpResponse = getResponseStr(getMethod, httpClient);
		return httpResponse == null ? "" : httpResponse.getResponseBody();
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求url
	 * @param httpClient
	 *            httpClient
	 * @param headers
	 *            头信息
	 * @param methodParams
	 *            请求方法额外参数
	 * @param requestParams
	 *            请求参数列表
	 * @param body
	 * @return
	 * @throws HttpRequestException
	 */
	public static String httpPost(String url, HttpClient httpClient, Map<String, String> headers,
			Map<String, Object> methodParams, List<NameValuePair[]> requestParams, String body)
					throws HttpRequestException {
		PostMethod postMethod = new PostMethod(url);
		setMethodHeader(postMethod, headers);
		setMethodParams(postMethod, methodParams);
		// 将表单的值放入postMethod中
		if (requestParams != null) {
			for (NameValuePair[] params : requestParams) {
				postMethod.setQueryString(params);
			}
		}

		if (body != null && !"".equals(body)) {
			InputStream input;
			try {
				input = new ByteArrayInputStream(body.getBytes("utf-8"));
				RequestEntity reis = new InputStreamRequestEntity(input);
				postMethod.setRequestEntity(reis);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		HttpResponse httpResponse = getResponseStr(postMethod, httpClient);
		return httpResponse == null ? "" : httpResponse.getResponseBody();

	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求url
	 * @param httpClient
	 *            httpClient
	 * @param headers
	 *            头信息
	 * @param methodParams
	 *            请求方法额外参数
	 * @param requestParams
	 *            请求参数
	 * @param body
	 * @return
	 * @throws HttpRequestException
	 */
	public static String httpPost(String url, HttpClient httpClient, Map<String, String> headers,
			Map<String, Object> methodParams, NameValuePair[] requestParam, String body) throws HttpRequestException {
		List<NameValuePair[]> requestParams = new ArrayList<NameValuePair[]>();
		if (requestParam != null) {
			requestParams.add(requestParam);
		}
		return httpPost(url, httpClient, headers, methodParams, requestParams, body);
	}

	/**
	 * 设置请求方法头信息
	 * 
	 * @param method
	 * @param headers
	 * @return
	 */
	private static HttpMethodBase setMethodHeader(HttpMethodBase method, Map<String, String> headers) {
		if (method == null) {
			throw new IllegalArgumentException("param getMethod is null");
		}

		if (headers != null) {
			for (String key : headers.keySet()) {
				method.addRequestHeader(key, headers.get(key));
			}
		}

		return method;
	}

	/**
	 * 设置请求方法参数，默认重试次数为0
	 * 
	 * @param method
	 * @param methodParams
	 * @return
	 */
	private static HttpMethodBase setMethodParams(HttpMethodBase method, Map<String, Object> methodParams) {
		if (method == null) {
			throw new IllegalArgumentException("param getMethod is null");
		}
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
		if (methodParams != null) {
			for (String key : methodParams.keySet()) {
				method.getParams().setParameter(key, methodParams.get(key));
			}
		}

		return method;
	}

	private static HttpResponse getResponseStr(HttpMethodBase method, HttpClient httpClient)
			throws HttpRequestException {
		if (httpClient == null) {
			return null;
		}
		HttpResponse httpResponse = null;
		URI uri = null;
		int statusCode = 0;
		try {
			uri = method.getURI();
			statusCode = httpClient.executeMethod(method);
			if (statusCode != 200) {
				throw new HttpRequestException("HTTPCODE_" + statusCode + "," + uri.toString());
			}
			httpResponse = new HttpResponse();
			httpResponse.setContentLength(method.getResponseContentLength());
			httpResponse.setHeaders(method.getRequestHeaders());
			httpResponse.setStatusLine(method.getStatusLine());
			httpResponse.setResponseBody(method.getResponseBodyAsString());

		} catch (Exception e) {
			throw new HttpRequestException(e.toString(), "HTTPCODE_" + statusCode + "," + uri.toString());
		} finally {
			// 关闭连接
			method.releaseConnection();
		}
		return httpResponse;
	}

	/**
	 * 将MAP转换成HTTP请求参数
	 * 
	 * @param pairArr
	 * @return
	 */
	public static NameValuePair[] praseParameterMap(Map<String, String> map) {
		if (map == null) {
			return new NameValuePair[0];
		}
		NameValuePair[] nvps = new NameValuePair[map.size()];

		Set<String> keys = map.keySet();
		int i = 0;
		for (String key : keys) {
			nvps[i] = new NameValuePair();
			nvps[i].setName(key);
			nvps[i].setValue(map.get(key));

			i++;
		}

		return nvps;
	}

	public static List<NameValuePair[]> praseParameterMap(List<Map<String, String>> mapList) {
		List<NameValuePair[]> values = new ArrayList<NameValuePair[]>();
		if (mapList != null) {
			for (Map<String, String> map : mapList) {
				values.add(praseParameterMap(map));
			}
		}
		return values;
	}

	public static String setQueryString(Map<String, String> map) {
		return EncodingUtil.formUrlEncode(praseParameterMap(map), "UTF-8");
	}

	public static String setQueryString(List<Map<String, String>> mapList) {
		String queryStr = "";
		if (mapList != null) {
			int i = 0;
			for (Map<String, String> map : mapList) {
				if (i > 0) {
					queryStr += "&";
				}
				queryStr += setQueryString(map);
				i++;
			}
		}
		return queryStr;
	}

}
