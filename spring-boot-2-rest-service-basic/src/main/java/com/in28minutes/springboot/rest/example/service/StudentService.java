package com.in28minutes.springboot.rest.example.service;

import com.in28minutes.springboot.rest.example.convertor.StudentConvertor;
import com.in28minutes.springboot.rest.example.dto.StudentDto;
import com.in28minutes.springboot.rest.example.entity.StudentEntity;
import com.in28minutes.springboot.rest.example.exception.StudentNotFoundException;
import com.in28minutes.springboot.rest.example.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired private StudentRepository studentRepository;
  @Autowired private GithubClientService githubClientService;
  @Autowired private StudentConvertor studentConvertor;

  public StudentEntity findById(Long id) {
    Optional<StudentEntity> student = studentRepository.findById(id);

    if (student.isPresent()) {
      return student.get();
    }
    throw new StudentNotFoundException("id-" + id);
  }

  public List<StudentEntity> findAll() {
    return studentRepository.findAll();
  }

  public StudentEntity save(StudentDto student) {
    StudentEntity studentEntity = studentConvertor.toEntity(student);
    studentEntity.setGithub(githubClientService.getUser(student.getName()));
    return studentRepository.save(studentEntity);
  }

  public StudentEntity update(StudentDto student, Long id) {
    StudentEntity studentEntity = studentConvertor.toEntity(student);
    findById(id);
    studentEntity.setId(id);
    return studentRepository.save(studentEntity);
  }

  public void deleteById(Long id) {
    studentRepository.deleteById(id);
  }
}
