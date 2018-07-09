package com.ljw.util.http;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.ljw.exception.HttpRequestException;
import com.ljw.util.encry.Md5Util;


public class HttpClientManager {

	private static Logger log = Logger.getLogger(HttpClientManager.class);

	private static final Map<String, HttpClient> clientMap = new ConcurrentHashMap<String, HttpClient>();

	/**
	 * 设置给定主机配置允许的默认最大连接数。 maximum number of connections allowed per host
	 */
	private Integer maxHostConnections;

	/**
	 * 设置允许的最大连接数 maximum number of connections allowed overall
	 */
	private Integer maxTotalConnections;

	/**
	 * 设置超时时间 the timeout until a connection is etablished
	 */
	private Integer connectionTimeOut;

	
	
	/**
	 * http get请求
	 * 
	 * @param url
	 *            请求url
	 * @param headers
	 *            头信息
	 * @param methodParams
	 *            请求方法额外参数
	 * @param params
	 *            请求参数
	 * @return
	 * @throws HttpRequestException
	 */
	public String httpGet(String url, Map<String, String> headers, Map<String, Object> methodParams,
			NameValuePair[] params) throws HttpRequestException {
		String result = HttpUtil.httpGet(url, getClient(url), headers, methodParams, params);
		log.fatal("url:" + url + ",getClient():" + getClient(url) + ",headers:" + headers + ",methodParams:"
				+ methodParams + ",params:" + params + ",result:" + result);
		return result;
	}

	/**
	 * http get请求
	 * 
	 * @param url
	 *            请求url
	 * @param headers
	 *            头信息
	 * @param methodParams
	 *            请求方法额外参数
	 * @param queryStr
	 *            请求参数信息，需urlencode且不能以？开头
	 * @return
	 * @throws HttpRequestException
	 */
	public String httpGet(String url, Map<String, String> headers, Map<String, Object> methodParams, String queryStr)
			throws HttpRequestException {
		String result = HttpUtil.httpGet(url, getClient(url), headers, methodParams, queryStr);
		log.fatal("url:" + url + ",getClient():" + getClient(url) + ",headers:" + headers + ",methodParams:"
				+ methodParams + ",queryStr:" + queryStr + ",result:" + result);
		return result;

	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求url
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
	public String httpPost(String url, Map<String, String> headers, Map<String, Object> methodParams,
			List<NameValuePair[]> requestParams, String body) throws HttpRequestException {
		String result = HttpUtil.httpPost(url, getClient(url), headers, methodParams, requestParams, body);
		log.fatal("url:" + url + ",getClient():" + getClient(url) + ",headers:" + headers + ",methodParams:"
				+ methodParams + ",requestParam:" + requestParams + ",body:" + body + ",result:" + result);
		return result;

	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求url
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
	public String httpPost(String url, Map<String, String> headers, Map<String, Object> methodParams,
			NameValuePair[] requestParam, String body) throws HttpRequestException {
		String result = HttpUtil.httpPost(url, getClient(url), headers, methodParams, requestParam, body);
		log.fatal("url:" + url + ",getClient():" + getClient(url) + ",headers:" + headers + ",methodParams:"
				+ methodParams + ",requestParam:" + requestParam + ",body:" + body + ",result:" + result);
		return result;

	}

	

	private HttpClient getClient(String url) {

		URI uri = null;
		String key = "";
		try {
			uri = getUri(url);
			
			log.error("uri:" + uri.toString());
			log.error("getHost: " + uri.getHost());

			key = getClientMapKey(uri.getHost());
			
		} catch (URIException e) {
			return null;
		}

		HttpClient client = clientMap.get(key);
		if (client != null) {
			return client;
		}
		client = createClient(uri);
		if (client != null) {
			clientMap.put(key, client);
		}
		return client;
	}

	private URI getUri(String url) throws URIException {
		
		GetMethod method = new GetMethod(url);
		return method.getURI();

	}
	
	private String getClientMapKey(String urlPrefix) {
		return Md5Util.md5CodeCommon(
				urlPrefix + "_" + maxHostConnections + "_" + maxTotalConnections + "_" + connectionTimeOut);
	}
	
	private HttpClient createClient(URI uri) {
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();

		params.setDefaultMaxConnectionsPerHost(maxHostConnections);
		params.setMaxTotalConnections(maxTotalConnections);
		params.setConnectionTimeout(connectionTimeOut);
		params.setSoTimeout(connectionTimeOut);

		connectionManager.setParams(params);

		HttpClient client = new HttpClient(connectionManager);
		HostConfiguration hostConfiguration = new HostConfiguration();

		HttpHost httpHost = null;
		try {
			httpHost = new HttpHost(uri.getHost());
		} catch (URIException e) {
			return null;
		}
		hostConfiguration.setHost(httpHost);

		client.setHostConfiguration(hostConfiguration);
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		return client;
	}

	public Integer getMaxHostConnections() {
		return maxHostConnections;
	}

	public void setMaxHostConnections(Integer maxHostConnections) {
		this.maxHostConnections = maxHostConnections;
	}

	public Integer getMaxTotalConnections() {
		return maxTotalConnections;
	}

	public void setMaxTotalConnections(Integer maxTotalConnections) {
		this.maxTotalConnections = maxTotalConnections;
	}

	public Integer getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	
}
