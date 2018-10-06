package com.in28minutes.springboot.rest.example.exception;

import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

import com.in28minutes.springboot.rest.example.dto.github.GithubResponse;
import com.in28minutes.springboot.rest.example.util.JsonUtil;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

public class GithubRestTemplateErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return (httpResponse.getStatusCode().series() == CLIENT_ERROR
        || httpResponse.getStatusCode().series() == SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    HttpStatus status = httpResponse.getStatusCode();

    if (status.series() == SERVER_ERROR || status.series() == CLIENT_ERROR) {
      if (REQUEST_TIMEOUT.equals(status)) {
        throw new BusinessException(
            Integer.toString(REQUEST_TIMEOUT.value()), REQUEST_TIMEOUT.name(), REQUEST_TIMEOUT);
      }

      String responseString =
          StreamUtils.copyToString(httpResponse.getBody(), StandardCharsets.UTF_8);
      val errorResponse = JsonUtil.parse(responseString, GithubResponse.class);

      if (Objects.isNull(errorResponse)) {
        throw new BusinessException(
            errorResponse.getErrors().get(0).getCode(), errorResponse.getMessage(), HttpStatus.OK);
      }

      throw new BusinessException(
          Integer.toString(HttpStatus.SERVICE_UNAVAILABLE.value()),
          HttpStatus.SERVICE_UNAVAILABLE.name(),
          HttpStatus.OK);
    }
  }
}
