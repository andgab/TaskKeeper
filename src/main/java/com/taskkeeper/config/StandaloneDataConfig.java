package com.taskkeeper.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile("dev")
public class StandaloneDataConfig implements DataConfig {

	@Bean
	public DataSource dataSource() throws SQLException {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    
    return builder.setType(EmbeddedDatabaseType.H2)
	    .addScript("classpath:/schema/schema.sql")
	    .addScript("classpath:/schema/init.sql")
	    .build();
	}
	
	@Bean
	public Properties jpaProperties() {
		
		return null;
	}

}
