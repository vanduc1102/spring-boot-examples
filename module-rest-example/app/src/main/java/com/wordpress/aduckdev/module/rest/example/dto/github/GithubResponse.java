package com.wordpress.aduckdev.module.rest.example.dto.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubResponse {

  private String message;

  @JsonProperty(value = "documentation_url")
  private String documentationUrl;

  private List<GithubError> errors;
}
