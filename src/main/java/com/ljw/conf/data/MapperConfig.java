package com.ljw.conf.data;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(DatabaseConfiguration.class)
public class MapperConfig {

	/**
	 * 莫名情况下，不识别Mapper的注解，找不到Mapper注解下的接口
	 * 
	 * 有说明认为是MapperScannerConfigurer执行创建bean比较早，所以需要重新进行注册
	 * 
	 * 重新声明一下这个接口可以解决
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer getBeanMSC(){
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.ljw.dao");
		msc.setAnnotationClass(Mapper.class);
		return msc;
	}
	
}
