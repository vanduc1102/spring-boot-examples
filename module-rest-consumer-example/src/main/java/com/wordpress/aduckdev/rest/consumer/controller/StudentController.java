package com.wordpress.aduckdev.rest.consumer.controller;


import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import com.wordpress.aduckdev.rest.consumer.service.StudentService;
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

  @Autowired
  private StudentService studentService;

  @GetMapping
  public List<StudentDto> getAllStudents() {
    return studentService.getStudents();
  }


  @PostMapping
  public ResponseEntity<StudentDto> saveStudent(@RequestBody @Valid StudentDto student) {
    StudentDto savedStudent = studentService.create(student);

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
    studentService.deteleById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateStudent(
      @RequestBody @Valid StudentDto student, @PathVariable Long id) {
    studentService.update(student, id);
    return ResponseEntity.noContent().build();
  }
}
