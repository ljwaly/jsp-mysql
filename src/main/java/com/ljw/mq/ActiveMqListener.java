package com.ljw.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ActiveMqListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			// 获取到接收的数据
			String text = ((TextMessage) message).getText();
			System.out.println(text);
			
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
