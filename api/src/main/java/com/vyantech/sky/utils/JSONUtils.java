/**
 * (C) VyanTech.com 2020
 */
package com.vyantech.sky.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONUtils {
	private final static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
	}

	public static String toJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}

	public static <T> T fromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, clazz);
	}
}
