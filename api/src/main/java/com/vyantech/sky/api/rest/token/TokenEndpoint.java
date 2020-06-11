/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.rest.token;

import static com.vyantech.sky.utils.CryptoUtils.decodeBase64;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vyantech.sky.api.rest.GenericEndpoint;
import com.vyantech.sky.api.rest.dto.TokenResponseDto;
import com.vyantech.sky.api.service.auth.TokenService;
import com.vyantech.sky.api.service.auth.User;
import com.vyantech.sky.api.service.auth.UserAuthService;

@Component
@Path("/token")
public class TokenEndpoint extends GenericEndpoint {
	private static final String HEADER_BASIC = "Basic";
	private static final int HEADER_BASIC_LENGTH = HEADER_BASIC.length() + 1;

	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private TokenService tokenService;

	@GET
	public Response getToken(@HeaderParam("Authorization") String authHeader) {

		if (StringUtils.isEmpty(authHeader) 
				|| !authHeader.startsWith(HEADER_BASIC)
				|| authHeader.length() <= HEADER_BASIC_LENGTH) {

			throwUnauthorizedException();
		}

		User user = getUserByBasicAuth(authHeader);
		if(user == null) {
			throwUnauthorizedException();
		}

		String token = tokenService.generateToken(user);
		if (token == null) {
			throw new InternalServerErrorException();
		}

		TokenResponseDto dto = new TokenResponseDto(token);
		return Response.ok(dto).build();
	}

	private User getUserByBasicAuth(String authHeader) {
		try {
			String password;
			String token = authHeader.substring(HEADER_BASIC_LENGTH);
			String[] tokenParts = decodeBase64(token).split(":");

			String email = tokenParts[0];
			password = tokenParts[1];

			return userAuthService.getByCredential(email, password);
		} catch (Exception ex) {
			return null;
		}
	}

	private static void throwUnauthorizedException() {
		throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
	}
}
