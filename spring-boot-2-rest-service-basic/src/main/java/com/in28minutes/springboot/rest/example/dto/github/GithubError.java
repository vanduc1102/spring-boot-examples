package com.in28minutes.springboot.rest.example.dto.github;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GithubError {
  private String resource;
  private String field;
  private String code;
}
