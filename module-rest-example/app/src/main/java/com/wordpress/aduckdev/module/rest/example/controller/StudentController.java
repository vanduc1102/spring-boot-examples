package com.wordpress.aduckdev.module.rest.example.controller;

import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import com.wordpress.aduckdev.module.rest.example.service.StudentService;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/api/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
  private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

  @Autowired private StudentService studentService;

  @GetMapping
  public List<StudentDto> getAllStudents() {
    return studentService.findAll();
  }

  @GetMapping("/async-list")
  public DeferredResult<ResponseEntity<?>> asyncGetAllStudents() {
    LOGGER.info("Received async-deferred-result request: " + System.nanoTime());
    DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

    ForkJoinPool.commonPool()
        .submit(
            () -> {
              LOGGER.info("Processing in separate thread: " + System.nanoTime());
              try {
                Thread.sleep(6000);
              } catch (InterruptedException e) {

              }
              output.setResult(ResponseEntity.ok(studentService.findAll()));
            });
    LOGGER.info("Servlet thread freed: " + System.nanoTime());
    return output;
  }

  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto student) {
    StudentDto savedStudent = studentService.save(student);

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
  public ResponseEntity<Void> updateStudent(
      @RequestBody @Valid StudentDto student, @PathVariable Long id) {
    studentService.update(student, id);
    return ResponseEntity.noContent().build();
  }
}
