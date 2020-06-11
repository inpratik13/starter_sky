/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.service.auth;

import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Override
	public User getByCredential(String email, String password) {
		//TODO: implement your user authentication
		if("abc".equals(email) && "xyz".equals(password)) {
			return new User(1, "ABC", Role.USER);
		}
		
		return null;
	}
}
