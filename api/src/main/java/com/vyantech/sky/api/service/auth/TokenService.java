/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.service.auth;

public interface TokenService {
	String generateToken(User user);
	
	Token asToken(String token);
	
	boolean isValidSignature(String token);
	
	boolean isExpired(Token token);
}
