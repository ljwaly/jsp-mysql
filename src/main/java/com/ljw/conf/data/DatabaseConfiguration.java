package com.ljw.conf.data;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;



/**
 * 配置数据源
 * @author PC
 * 在application.yml中设置属性对sqlsessionFactory的进行自动装配
 * 
 */
@Configuration
public class DatabaseConfiguration {


	
//	@Bean(name = "test1DataSource")
//    @Primary
//    public DataSource testDataSource() throws InterruptedException {
//         DataSource dataSource = DataSourceBuilder.create()
//        		.url(url)
//        		.username(username)
//        		.password(password)
//        		.driverClassName(driverClassName)
//        		.build();
//         
//         return dataSource;
//    }

  
	
    @Bean
    @Primary
    public DataSourceTransactionManager testTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
}
