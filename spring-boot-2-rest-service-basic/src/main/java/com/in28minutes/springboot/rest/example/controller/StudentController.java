package com.in28minutes.springboot.rest.example.controller;

import com.in28minutes.springboot.rest.example.dto.StudentDto;
import com.in28minutes.springboot.rest.example.entity.StudentEntity;
import com.in28minutes.springboot.rest.example.service.StudentService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

  @Autowired private StudentService studentService;

  @GetMapping
  public List<StudentDto> getAllStudents() {
    return studentService.findAll();
  }

  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto student) {
    StudentEntity savedStudent = studentService.save(student);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedStudent.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping("/{id}")
  public StudentDto getStudent(@PathVariable Long id) {
    return studentService.findById(id);
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable Long id) {
    studentService.deleteById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentEntity> updateStudent(
      @RequestBody @Valid StudentDto student, @PathVariable Long id) {
    studentService.update(student, id);
    return ResponseEntity.noContent().build();
  }
}
