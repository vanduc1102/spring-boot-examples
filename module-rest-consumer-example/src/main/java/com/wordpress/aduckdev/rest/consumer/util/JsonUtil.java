package com.wordpress.aduckdev.rest.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

  private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private JsonUtil() {}

  public static String stringify(Object object) {
    try {
      return getObjectMapper().writeValueAsString(object);
    } catch (JsonProcessingException e) {
      LOGGER.error("Error writing object as Json.", e);
    }
    return null;
  }

  public static <T> T parse(String value, Class<T> clazz) {
    try {
      return getObjectMapper().readValue(value, clazz);
    } catch (Exception e) {
      LOGGER.error("Error parsing Json as {}", clazz.getName(), e);
      return null;
    }
  }

  public static ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }
}
