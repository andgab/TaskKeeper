package com.taskkeeper.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:/properties/database.properties")
@Profile("production")
public class MysqlDataConfig implements DataConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() throws SQLException {
		//TODO: Add a JNDI DataSource provided by the JEE2 container (Tomcat)
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName") );
		dataSource.setUrl(env.getRequiredProperty("jdbc.url") );
		dataSource.setUsername(env.getRequiredProperty("jdbc.username") );
		dataSource.setPassword(env.getRequiredProperty("jdbc.password") );
		
		return dataSource;
	}
	
	@Bean
	public Properties jpaProperties() {
    Properties properties = new Properties();  
   
    properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));  
    properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));  
    return properties;  
	}

}
