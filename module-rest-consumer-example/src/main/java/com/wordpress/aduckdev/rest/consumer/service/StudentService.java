package com.wordpress.aduckdev.rest.consumer.service;

import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {

  @Autowired
  @Qualifier(value = "moduleExampleRestTemplate")
  private RestTemplate restTemplate;

  @Value(value = "${app.module-rest-example.client.endpoint}")
  private String serviceUrl;


  public List<StudentDto> getStudents() {
    return restTemplate
        .getForObject(serviceUrl.concat("/api/v1/students"), List.class);
  }

  public StudentDto findById(Long studentId) {
    return restTemplate
        .getForObject(serviceUrl.concat("/api/v1/students/").concat(studentId.toString()),
            StudentDto.class);
  }

  public void deteleById(Long studentId) {
    restTemplate
        .delete(serviceUrl.concat("/api/v1/students/").concat(studentId.toString()));
  }

  public StudentDto create(StudentDto studentDto) {
    return restTemplate
        .postForObject(serviceUrl.concat("/api/v1/students"), studentDto, StudentDto.class);
  }

  public StudentDto update(StudentDto studentDto, Long id) {
    return restTemplate
        .postForObject(serviceUrl.concat("/api/v1/students").concat(id.toString()), studentDto,
            StudentDto.class);
  }
}
