package com.in28minutes.springboot.rest.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GenericResponse<T> {

  private String code;
  private String message;
  private T data;

  public GenericResponse(String code, String message) {
    this.code = code;
    this.message = message;
    this.data = null;
  }
}
