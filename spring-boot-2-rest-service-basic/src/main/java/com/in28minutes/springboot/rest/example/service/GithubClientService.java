package com.in28minutes.springboot.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClientService {

  @Value(value = "${app.github.client.endpoint}")
  private String githubEndpoint;

  @Autowired
  @Qualifier(value = "gitHubRestTemplate")
  private RestTemplate restTemplate;

  public String getUser(String username) {
    return restTemplate.getForObject(
        githubEndpoint.concat("users/").concat(username), String.class);
  }
}
