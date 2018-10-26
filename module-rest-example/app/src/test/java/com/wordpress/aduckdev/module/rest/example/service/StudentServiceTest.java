package com.wordpress.aduckdev.module.rest.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.wordpress.aduckdev.module.rest.example.dto.StudentDto;
import com.wordpress.aduckdev.module.rest.example.entity.StudentEntity;
import com.wordpress.aduckdev.module.rest.example.exception.StudentNotFoundException;
import com.wordpress.aduckdev.module.rest.example.repository.StudentRepository;
import com.wordpress.aduckdev.module.rest.example.util.PodamUtil;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

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
  public void testSaveStudent() throws Exception {
    String GITHUB_PROFILE = "{}";
    StudentDto studentDto = PodamUtil.manufacturePojo(StudentDto.class);
    studentDto.setId(null);
    StudentEntity entity = Whitebox.invokeMethod(studentService, "toEntity", studentDto);
    when(githubClientService.getUser(studentDto.getUsername())).thenReturn(GITHUB_PROFILE);
    when(studentRepository.save(any(StudentEntity.class))).thenReturn(entity);
    studentService.save(studentDto);
  }
}
