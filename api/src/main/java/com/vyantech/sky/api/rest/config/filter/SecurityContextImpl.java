/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.rest.config.filter;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.vyantech.sky.api.service.auth.Token;

class SecurityContextImpl implements SecurityContext {
	private static final String AUTH_SCHEMA_JWT = "JWT";
	private final Token token;
	private final Principal principal;

	SecurityContextImpl(Token tokenInfo) {
		this.token = tokenInfo;
		this.principal = () -> tokenInfo.getName();
	}

	@Override
	public String getAuthenticationScheme() {
		return AUTH_SCHEMA_JWT;
	}

	@Override
	public Principal getUserPrincipal() {
		return principal;
	}

	@Override
	public boolean isSecure() {
		return true;
	}

	@Override
	public boolean isUserInRole(String role) {
		return token.getRole().name().equals(role);
	}

}