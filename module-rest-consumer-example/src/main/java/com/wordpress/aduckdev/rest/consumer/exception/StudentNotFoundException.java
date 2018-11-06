package com.wordpress.aduckdev.rest.consumer.exception;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BusinessException {
  public StudentNotFoundException(String exception) {
    super(HttpStatus.NOT_FOUND.name(), exception, HttpStatus.NOT_FOUND);
  }
}
