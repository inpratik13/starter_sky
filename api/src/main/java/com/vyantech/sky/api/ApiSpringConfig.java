package com.vyantech.sky.api;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.vyantech.sky.api.rest.RestSpringConfig;
import com.vyantech.sky.core.CoreSpringConfig;
import com.vyantech.sky.core.db.DBConfig;

@Component
@Configuration
@ComponentScan(basePackages = { "com.vyantech.sky.api.service" })
@Import({ CoreSpringConfig.class, RestSpringConfig.class })
public class ApiSpringConfig {

	@Bean
	public DBConfig persistenceConfig() throws IOException {
		Properties prop = new Properties();
		prop.load(
				ApiSpringConfig.class
						.getResourceAsStream("/persistence.properties"));

		return DBConfig.from(prop);
	}
}
