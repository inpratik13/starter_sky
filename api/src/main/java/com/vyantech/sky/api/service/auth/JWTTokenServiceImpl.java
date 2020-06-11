/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.api.service.auth;

import static com.vyantech.sky.utils.CryptoUtils.decodeBase64;
import static com.vyantech.sky.utils.CryptoUtils.encodeBase64;
import static com.vyantech.sky.utils.JSONUtils.fromJson;
import static com.vyantech.sky.utils.JSONUtils.toJson;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vyantech.sky.utils.HmacHelper;

@Service
public class JWTTokenServiceImpl implements TokenService {
	private static final Logger logger = LoggerFactory
			.getLogger(JWTTokenServiceImpl.class);

	private static final long TOKEN_LIFE_HOURS = 10L;
	private final HmacHelper hmacHelper;

	@Autowired
	public JWTTokenServiceImpl(
			@Value("${outing.auth.token_generator_secret_key}") String secretKey) {
		hmacHelper = new HmacHelper(secretKey);
	}

	@Override
	public Token asToken(String token) {
		if (token != null) {
			try {
				return fromJson(
						decodeBase64(token.split("\\.")[0]),
						Token.class);

			} catch (Exception e) {
				logger.error("Error while parsing token. Token:[{}]",
						token, e);
			}
		}

		return null;
	}

	@Override
	public boolean isValidSignature(String jwt) {
		if (jwt == null)
			return false;

		try {
			String[] parts = jwt.split("\\.");
			String jsonPart = parts[0];
			String tokenPart = parts[1];

			if (hmacHelper.encode(jsonPart).equals(tokenPart)) {
				return true;
			}

		} catch (Exception e) {
			logger.error("Exception while validating token[{}]", jwt, e);
		}

		return false;
	}

	@Override
	public String generateToken(User user) {
		try {
			Token token = new Token(
					user, 
					LocalDateTime.now().plusHours(TOKEN_LIFE_HOURS));

			String tokenString = encodeBase64(toJson(token));
			return tokenString + "." + hmacHelper.encode(tokenString);

		} catch (Exception e) {
			logger.error(
					"Exception while generating token for user [User:{}].",
					user.getId(), e);
		}
		
		return null;
	}

	@Override
	public boolean isExpired(Token token) {
		return token == null
				|| token.getExpires() == null
				|| token.getExpires().before(new Date());
	}

}
