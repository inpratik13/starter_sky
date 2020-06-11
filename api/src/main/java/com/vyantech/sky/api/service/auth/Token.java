/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.service.auth;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Token {
	private int id;
	private String name;
	private Role role;
	private Date expires;

	public Token() {
	}

	public Token(User user, LocalDateTime expires) {
		this.id = user.getId();
		this.name = user.getName();
		this.role = user.getRole();
		this.expires = Date
				.from(expires.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Token(int id, String name, Role role, LocalDateTime expires) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.expires = Date
				.from(expires.atZone(ZoneId.systemDefault()).toInstant());
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Role getRole() {
		return role;
	}

	public Date getExpires() {
		return expires;
	}

}