/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.utils;

import java.util.Base64;

public class CryptoUtils {

	public static String encodeBase64(String plainString) {
		if (plainString == null)
			return null;

		return encodeBase64(plainString.getBytes());
	}

	public static String encodeBase64(byte[] bytes) {
		if (bytes == null)
			return null;

		return Base64.getEncoder().encodeToString(bytes);
	}

	public static String decodeBase64(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString));
	}
}
