package com.in28minutes.springboot.rest.example.service;

import com.in28minutes.springboot.rest.example.convertor.StudentConverter;
import com.in28minutes.springboot.rest.example.dto.StudentDto;
import com.in28minutes.springboot.rest.example.entity.StudentEntity;
import com.in28minutes.springboot.rest.example.exception.StudentNotFoundException;
import com.in28minutes.springboot.rest.example.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired private StudentRepository studentRepository;
  @Autowired private GithubClientService githubClientService;
  @Autowired private StudentConverter studentConverter;

  public StudentDto findById(Long id) {
    Optional<StudentEntity> student = studentRepository.findById(id);

    if (student.isPresent()) {
      return studentConverter.toDto(student.get());
    }
    throw new StudentNotFoundException("id-" + id);
  }

  public List<StudentDto> findAll() {
    return studentRepository
        .findAll()
        .stream()
        .map(e -> studentConverter.toDto(e))
        .collect(Collectors.toList());
  }

  public StudentEntity save(StudentDto student) {
    StudentEntity studentEntity = studentConverter.toEntity(student);
    studentEntity.setGithub(githubClientService.getUser(student.getUsername()));
    return studentRepository.save(studentEntity);
  }

  public StudentDto update(StudentDto student, Long id) {
    StudentEntity studentEntity = studentConverter.toEntity(student);
    findById(id);
    studentEntity.setId(id);
    return studentConverter.toDto(studentRepository.save(studentEntity));
  }

  public void deleteById(Long id) {
    studentRepository.deleteById(id);
  }
}
