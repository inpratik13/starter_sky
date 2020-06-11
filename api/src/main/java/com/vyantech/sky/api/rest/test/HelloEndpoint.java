package com.vyantech.sky.api.rest.test;

import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableMap;
import com.vyantech.sky.api.rest.GenericEndpoint;
import com.vyantech.sky.api.service.auth.Role;

@Singleton
@Path("test")
public class HelloEndpoint extends GenericEndpoint {
	
	@GET
	@Path("helloAnonymous")
	public Map<String, String> helloAnonymous(@QueryParam("name") String name) {
		String sufix = StringUtils.firstNonBlank(name, "World!");
		String message = "Hello Anonymous " + sufix + "!";
		return ImmutableMap.of("message", message);
	}
	
	@GET
	@Path("helloUser")
	@RolesAllowed(value = Role.Names.USER)
	public Map<String, String> helloUser(@QueryParam("name") String name) {
		String sufix = StringUtils.firstNonBlank(name, "World!");
		String message = "Hello User " + sufix + "!";
		return ImmutableMap.of("message", message);
	}
}
