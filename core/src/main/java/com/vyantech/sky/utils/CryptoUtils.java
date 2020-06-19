/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.utils;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {
	private static MessageDigest SHA256;
	static {
		try {
			SHA256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new Error("SHA256 algorithm not found.", e);
		}
	}

	public static String encodeBase64(String plainString) {
		if (plainString == null) {
			return null;
		}

		return encodeBase64(plainString.getBytes());
	}

	public static String sha256(String plainString) {
		if (plainString == null) {
			return null;
		}

		return bytesToHex(
				SHA256.digest(plainString.getBytes(UTF_8)));
	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static String encodeBase64(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		return getEncoder().encodeToString(bytes);
	}

	public static String decodeBase64(String encodedString) {
		return new String(getDecoder().decode(encodedString));
	}
}
