/**
 * (C) VyanTech.com Ltd 2020
 */
package com.vyantech.sky.api.rest.config.filter;

import static javax.ws.rs.Priorities.AUTHENTICATION;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import com.vyantech.sky.api.service.auth.Token;
import com.vyantech.sky.api.service.auth.TokenService;


@Provider
@Priority(AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	private static final String HEADER_AUTH = "Authorization";
	private static final String BEARER_KEY = "Bearer";
	private static final int BEARER_KEY_LENGTH = BEARER_KEY.length() + 1;

	@Autowired
	private TokenService tokenService;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {

		String authHeader = context.getHeaderString(HEADER_AUTH);
		String authToken = getTokenFromBearerAuthHeader(authHeader);

		if (authToken == null) {
			return;
		}

		if (!tokenService.isValidSignature(authToken)) {
			throwNotAuthorizedException();
		}

		Token token = tokenService.asToken(authToken);
		if (tokenService.isExpired(token)) {
			throwNotAuthorizedException();
		}

		context.setSecurityContext(new SecurityContextImpl(token));
	}

	private static String getTokenFromBearerAuthHeader(String authHeader) {
		if (authHeader == null
				|| !authHeader.startsWith(BEARER_KEY)
				|| authHeader.length() < BEARER_KEY_LENGTH) {
			return null;
		}

		return authHeader.substring(BEARER_KEY_LENGTH);
	}

	private static void throwNotAuthorizedException() {
		throw new NotAuthorizedException(
				Response.status(Response.Status.UNAUTHORIZED).build());
	}

}
