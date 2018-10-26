package com.wordpress.aduckdev.module.rest.example.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import com.wordpress.aduckdev.module.rest.example.exception.StudentNotFoundException;
import com.wordpress.aduckdev.module.rest.example.service.StudentService;
import com.wordpress.aduckdev.module.rest.example.util.PodamUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

  @Mock private StudentService studentService;
  @InjectMocks private StudentController studentController;

  private static StudentDto studentDto;

  @Before
  public void init() {
    studentDto = PodamUtil.manufacturePojo(StudentDto.class);
  }

  @Test
  public void testGetStudentId_ok() {
    Long studentId = studentDto.getId();
    when(studentService.findById(studentId)).thenReturn(studentDto);
    StudentDto studentResponse = studentController.getStudent(studentId);
    assertThat(studentResponse.getId()).isEqualTo(studentId);
  }

  @Test
  public void testGetStudentId_exception() {
    Long studentId = anyLong();
    when(studentService.findById(studentId)).thenThrow(StudentNotFoundException.class);
    assertThatThrownBy(
            () -> {
              studentController.getStudent(studentId);
            })
        .isInstanceOf(StudentNotFoundException.class);
  }
}
