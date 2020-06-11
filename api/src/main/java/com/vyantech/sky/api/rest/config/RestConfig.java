/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.rest.config;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestConfig extends ResourceConfig {
	private static final Logger logger = LoggerFactory
			.getLogger(RestConfig.class);

	public RestConfig() {
		logger.debug("Loading Custom Jersey configuration.");

		logger.debug("\tRegistering LoggingFeature");
		register(LoggingFeature.class);

		logger.debug("\tRegistering RolesAllowedDynamicFeature");
		register(RolesAllowedDynamicFeature.class);

		// logger.debug("\tRegistering MultiPartFeature");
		// register(MultiPartFeature.class);

		// System.setProperty("org.jboss.logging.provider","slf4j");

		logger.debug(
				"\tsetting server property BV_SEND_ERROR_IN_RESPONSE to true");
		// true = validation errors to be sent to the client.
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

		logger.debug(
				"\tsetting server property RESPONSE_SET_STATUS_OVER_SEND_ERROR to true");
		property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, "true");

//		register(new AbstractBinder() {
//			@Override
//			protected void configure() {
//				bindFactory(LoginContextProvider.class).to(Login.class)
//				.proxy(true).proxyForSameScope(false).in(RequestScoped.class);
//			}
//		});

	}

}