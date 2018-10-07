package com.in28minutes.springboot.rest.example.service;

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

  public StudentDto findById(Long id) {
    Optional<StudentEntity> student = studentRepository.findById(id);

    if (student.isPresent()) {
      return toDto(student.get());
    }
    throw new StudentNotFoundException("id-" + id);
  }

  public List<StudentDto> findAll() {
    return studentRepository.findAll().stream().map(e -> toDto(e)).collect(Collectors.toList());
  }

  public StudentEntity save(StudentDto student) {
    StudentEntity studentEntity = toEntity(student);
    studentEntity.setGithub(githubClientService.getUser(student.getUsername()));
    return studentRepository.save(studentEntity);
  }

  public StudentDto update(StudentDto student, Long id) {
    StudentEntity studentEntity = toEntity(student);
    findById(id);
    studentEntity.setId(id);
    return toDto(studentRepository.save(studentEntity));
  }

  public void deleteById(Long id) {
    studentRepository.deleteById(id);
  }

  private StudentEntity toEntity(StudentDto studentDto) {
    StudentEntity entity = new StudentEntity();
    entity.setEmail(studentDto.getEmail());
    entity.setId(studentDto.getId());
    entity.setUsername(studentDto.getUsername());
    entity.setDisplayName(studentDto.getDisplayName());
    entity.setPassportNumber(studentDto.getPassportNumber());
    entity.setPhoneNumber(studentDto.getPhoneNumber());
    return entity;
  }

  private StudentDto toDto(StudentEntity studentEntity) {
    StudentDto dto = new StudentDto();
    dto.setEmail(studentEntity.getEmail());
    dto.setId(studentEntity.getId());
    dto.setUsername(studentEntity.getUsername());
    dto.setDisplayName(studentEntity.getDisplayName());
    dto.setPassportNumber(studentEntity.getPassportNumber());
    dto.setPhoneNumber(studentEntity.getPhoneNumber());
    return dto;
  }
}
