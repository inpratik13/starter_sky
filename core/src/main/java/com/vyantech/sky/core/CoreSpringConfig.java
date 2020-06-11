package com.vyantech.sky.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vyantech.sky.core.db.DBSpringConfig;

@Configuration
@ComponentScan(basePackages = { "com.vyantech.sky.core.services" })
//@PropertySources({
//		@PropertySource("classpath:com/vyantech/sky/core/db/db-${envTarget:dev}.properties")
//})
@Import(DBSpringConfig.class)
public class CoreSpringConfig {

	
}
