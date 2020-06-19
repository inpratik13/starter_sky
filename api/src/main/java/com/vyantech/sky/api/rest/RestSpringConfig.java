/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:rest-${envTarget:dev}.properties")
})
public class RestSpringConfig {

}
