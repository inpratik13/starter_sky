/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.core;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vyantech.sky.core.db.DBConfig;

@Configuration
@Import(CoreSpringConfig.class)
public class CoreTestSpringConfig {

	@Bean
	public DBConfig createPersistenceConfig() throws IOException {
		Properties prop = getProperties();
		return DBConfig.from(prop);
	}

	private Properties getProperties() throws IOException {
		Properties prop = new Properties();
		prop.load(getClass()
						.getResourceAsStream("/db.properties"));
		return prop;
	}

}
