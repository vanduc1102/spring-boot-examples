package com.in28minutes.springboot.rest.example.convertor;

import com.in28minutes.springboot.rest.example.dto.StudentDto;
import com.in28minutes.springboot.rest.example.entity.StudentEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentConvertor {

  public StudentEntity toEntity(StudentDto studentDto) {
    StudentEntity entity = new StudentEntity();
    entity.setEmail(studentDto.getEmail());
    entity.setId(studentDto.getId());
    entity.setName(studentDto.getName());
    entity.setPassportNumber(studentDto.getPassportNumber());
    entity.setPhoneNumber(studentDto.getPhoneNumber());
    return entity;
  }

  public StudentDto toDto(StudentEntity studentEntity) {
    StudentDto dto = new StudentDto();
    dto.setEmail(studentEntity.getEmail());
    dto.setId(studentEntity.getId());
    dto.setName(studentEntity.getName());
    dto.setPassportNumber(studentEntity.getPassportNumber());
    dto.setPhoneNumber(studentEntity.getPhoneNumber());
    return dto;
  }
}
