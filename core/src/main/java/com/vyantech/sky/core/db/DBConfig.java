package com.vyantech.sky.core.db;

import java.util.Properties;

/**
 * (C) VyanTech.com 2020
 */

public class DBConfig {
	private final String driverClassName, username, password;
	private final String dbURL;
	private final Properties hibernateProperties;

	public DBConfig(String driverClassName, String dbURL,
			String username,
			String password,
			Properties hibernateProperties) {

		this.driverClassName = driverClassName;
		this.username = username;
		this.password = password;
		this.dbURL = dbURL;
		this.hibernateProperties = hibernateProperties;
	}

	public static DBConfig from(Properties prop) {
		String driverClassName = prop.getProperty("driver");
		String dbURL = prop.getProperty("url");
		String username = prop.getProperty("user");
		String password = prop.getProperty("password");

		return new DBConfig(driverClassName,
				dbURL,
				username,
				password,
				prop);

	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDbURL() {
		return dbURL;
	}

	public Properties getHibernateProperties() {
		return hibernateProperties;
	}

}
