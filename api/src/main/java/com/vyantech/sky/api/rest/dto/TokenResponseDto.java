/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.rest.dto;

public class TokenResponseDto {
	private String token;

	public TokenResponseDto() {
	}

	public TokenResponseDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
