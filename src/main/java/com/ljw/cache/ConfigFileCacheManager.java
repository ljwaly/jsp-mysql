package com.ljw.cache;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ljw.util.Configuration;

@Component
public class ConfigFileCacheManager {
	
	@Value("${sdkljw}")
	private String sdkljw;
	
	private static Configuration conf;

	@PostConstruct
	public void init(){
		conf=Configuration.getInstance();
		System.out.println("sljflajjfajkd******************************");
		System.out.println(sdkljw);
	}

	

	public String getProperty(String key) {
		return conf.getProperty(key);
	}
	
	
	
}
