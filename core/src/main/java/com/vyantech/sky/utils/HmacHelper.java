/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.utils;

import static com.vyantech.sky.utils.CryptoUtils.encodeBase64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacHelper {
	private static final String HMAC_ALGO = "HmacSHA256";
	private final Mac hmac;

	public HmacHelper(String secretKey) {
		try {
			this.hmac = Mac.getInstance(HMAC_ALGO);
			this.hmac.init(new SecretKeySpec(secretKey.getBytes(), HMAC_ALGO));

		} catch (InvalidKeyException e) {
			throw new IllegalStateException(
					"failed to initialize HMAC. Invalid key. Message: "
							+ e.getMessage(),
					e);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"failed to initialize HMAC. Invalid algorithm. Message: "
							+ e.getMessage(),
					e);
		}
	}

	public String encode(String plain) {
		if (plain == null)
			return null;

		return encodeBase64(hmac.doFinal(plain.getBytes()));
	}
}
