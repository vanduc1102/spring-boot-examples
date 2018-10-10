package com.in28minutes.springboot.rest.example.util;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PodamUtil {
  private static PodamFactory factory = new PodamFactoryImpl();

  private PodamUtil() {}

  public static <T> T manufacturePojo(Class<T> clazz) {
    return factory.manufacturePojo(clazz);
  }
}
