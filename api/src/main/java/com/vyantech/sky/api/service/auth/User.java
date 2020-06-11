/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.service.auth;

public class User {
	private final int id;
	private final String name;
	private final Role role;

	public User(int id, String name, Role role) {
		this.id = id;
		this.name = name;
		this.role = role;
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

}