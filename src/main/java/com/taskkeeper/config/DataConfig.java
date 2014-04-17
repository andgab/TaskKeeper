package com.taskkeeper.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

public interface DataConfig {
	public DataSource dataSource() throws SQLException;
	
	public Properties jpaProperties();
}
