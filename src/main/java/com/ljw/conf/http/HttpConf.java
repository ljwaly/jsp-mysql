package com.ljw.conf.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.ljw.util.http.HttpClientManager;

@Configuration
public class HttpConf {
	
	
	/**
	 * 设置给定主机配置允许的默认最大连接数。 maximum number of connections allowed per host
	 */
	@Value("${http.maxHostConnections}")
	private Integer maxHostConnections;

	/**
	 * 设置允许的最大连接数 maximum number of connections allowed overall
	 */
	@Value("${http.maxTotalConnections}")
	private Integer maxTotalConnections;

	/**
	 * 设置超时时间 the timeout until a connection is etablished
	 */
	@Value("${http.connectionTimeOut}")
	private Integer connectionTimeOut;
	
	@Bean("default")
	@Primary
	public HttpClientManager getHttpClientManager(){
		HttpClientManager httpClientManager = new HttpClientManager();
		httpClientManager.setMaxHostConnections(maxHostConnections);
		httpClientManager.setMaxTotalConnections(maxTotalConnections);
		httpClientManager.setConnectionTimeOut(connectionTimeOut);
		return httpClientManager;
		
	}
	
	
	@Bean("httpClientManager5000")
	public HttpClientManager getSpecialHttpClientManager(){
		HttpClientManager httpClientManager = new HttpClientManager();
		httpClientManager.setMaxHostConnections(maxHostConnections);
		httpClientManager.setMaxTotalConnections(maxTotalConnections);
		httpClientManager.setConnectionTimeOut(5000);
		return httpClientManager;
		
	}
}
