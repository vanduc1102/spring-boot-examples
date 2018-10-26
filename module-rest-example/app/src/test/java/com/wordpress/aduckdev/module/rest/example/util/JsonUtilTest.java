package com.wordpress.aduckdev.module.rest.example.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.jayway.jsonpath.JsonPath;
import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import org.junit.Test;

public class JsonUtilTest {

  @Test
  public void test_stringify_ok() {
    StudentDto studentDto = PodamUtil.manufacturePojo(StudentDto.class);
    String json = JsonUtil.stringify(studentDto);
    assertThat(JsonPath.parse(json).read("$.username").toString())
        .isEqualTo(studentDto.getUsername());
  }

  @Test
  public void test_stringify_null() {
    String json = JsonUtil.stringify(null);
    assertThat(json).isEqualTo("null");
  }

  @Test
  public void test_parse_ok() {
    StudentDto studentDto = PodamUtil.manufacturePojo(StudentDto.class);
    String json = JsonUtil.stringify(studentDto);
    assertThat(JsonUtil.parse(json, StudentDto.class).getId()).isEqualTo(studentDto.getId());
  }

  @Test
  public void test_parse_error() {
    String json = "{\"username\"";
    assertThat(JsonUtil.parse(json, StudentDto.class)).isNull();
  }
}
