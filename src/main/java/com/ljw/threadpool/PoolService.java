package com.ljw.threadpool;

import org.springframework.stereotype.Service;

@Service
public class PoolService {

	public String sendMessage(String message){
		System.out.println("pool="+message);
		return "success";
	}
	
}
