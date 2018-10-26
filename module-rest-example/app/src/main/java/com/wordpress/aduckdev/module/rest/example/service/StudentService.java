package com.wordpress.aduckdev.module.rest.example.service;

import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import com.wordpress.aduckdev.module.rest.example.entity.StudentEntity;
import com.wordpress.aduckdev.module.rest.example.exception.StudentNotFoundException;
import com.wordpress.aduckdev.module.rest.example.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
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

  public StudentDto save(StudentDto student) {
    StudentEntity studentEntity = toEntity(student);
    studentEntity.setGithub(githubClientService.getUser(student.getUsername()));
    Long studentId = studentRepository.save(studentEntity).getId();
    student.setId(studentId);
    return student;
  }

  public StudentDto update(StudentDto student, Long id) {
    StudentEntity studentEntity = toEntity(student);
    findById(id);
    studentEntity.setId(id);
    return toDto(studentRepository.save(studentEntity));
  }

  public void deleteById(Long id) {
    LOGGER.debug("random debug message for unit-test");
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
