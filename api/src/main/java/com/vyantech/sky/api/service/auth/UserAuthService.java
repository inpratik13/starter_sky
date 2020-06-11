/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.service.auth;

public interface UserAuthService {
	User getByCredential(String email, String password);
}
