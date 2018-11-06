package com.wordpress.aduckdev.rest.consumer.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
  private String code;
  private String message;
  private HttpStatus status;
}
