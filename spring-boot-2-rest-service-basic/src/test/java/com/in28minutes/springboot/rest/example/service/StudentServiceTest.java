package com.in28minutes.springboot.rest.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.in28minutes.springboot.rest.example.dto.StudentDto;
import com.in28minutes.springboot.rest.example.entity.StudentEntity;
import com.in28minutes.springboot.rest.example.exception.StudentNotFoundException;
import com.in28minutes.springboot.rest.example.repository.StudentRepository;
import com.in28minutes.springboot.rest.example.util.PodamUtil;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

  @Mock private StudentRepository studentRepository;
  @Mock private GithubClientService githubClientService;
  @Spy @InjectMocks private StudentService studentService = new StudentService();

  private StudentEntity studentEntity;

  @Before
  public void init() {
    studentEntity = PodamUtil.manufacturePojo(StudentEntity.class);
  }

  @Test
  public void testGetStudentId_ok() {
    Long studentId = studentEntity.getId();
    Optional<StudentEntity> studentEntityOptional = Optional.of(studentEntity);
    when(studentRepository.findById(studentId)).thenReturn(studentEntityOptional);
    StudentDto studentResponse = studentService.findById(studentId);
    assertThat(studentResponse.getId()).isEqualTo(studentId);
  }

  @Test
  public void testGetStudentId_NotFound() {
    Long studentId = studentEntity.getId();
    Optional<StudentEntity> studentEntityOptional = Optional.empty();
    when(studentRepository.findById(studentId)).thenReturn(studentEntityOptional);
    assertThatThrownBy(
            () -> {
              studentService.findById(studentId);
            })
        .isInstanceOf(StudentNotFoundException.class)
        .hasFieldOrPropertyWithValue("message", "id-" + studentId);
  }

  @Test
  public void testSaveStudent() {
    StudentDto studentDto = PodamUtil.manufacturePojo(StudentDto.class);
    when(githubClientService.getUser(studentDto.getUsername())).thenReturn("randomString");
    studentService.save(studentDto);
  }
}
