package com.in28minutes.springboot.rest.example.validation;

import static org.assertj.core.api.Assertions.assertThat;

import com.in28minutes.springboot.rest.example.constant.StudentValidationMessage;
import com.in28minutes.springboot.rest.example.dto.StudentDto;
import com.in28minutes.springboot.rest.example.util.PodamUtil;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.val;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentDtoValidationTest {
  private StudentDto studentDto;
  private static ValidatorFactory validatorFactory;
  private static Validator validator;

  @BeforeClass
  public static void createValidator() {
    validatorFactory = Validation.buildDefaultValidatorFactory();
    validator = validatorFactory.getValidator();
  }

  @AfterClass
  public static void afterClass() {
    validatorFactory.close();
  }

  @Before
  public void beforeTest() {
    studentDto = PodamUtil.manufacturePojo(StudentDto.class);
    studentDto.setPassportNumber("PQ234234");
    studentDto.setPhoneNumber("0987654321");
    studentDto.setEmail("ass@gmail.com");
  }

  @Test
  public void shouldValid() {
    val violations = validator.validate(studentDto);
    assertThat(violations).isEmpty();
  }

  @Test
  public void shouldNotValidEmail() {
    studentDto.setEmail("assgmail.com");
    val violations = validator.validate(studentDto);
    assertThat(violations.iterator().next().getMessage())
        .isEqualTo(StudentValidationMessage.EMAIL_VALIDATION);
  }

  @Test
  public void shouldNotValidPhone() {
    studentDto.setPhoneNumber("xxxx");
    val violations = validator.validate(studentDto);
    assertThat(violations.iterator().next().getMessage())
        .isEqualTo(StudentValidationMessage.PHONE_NUMBER_VALIDATION);
  }

  @Test
  public void shouldNotValidPassport() {
    studentDto.setPassportNumber(null);
    val violations = validator.validate(studentDto);
    assertThat(violations.iterator().next().getMessage())
        .isEqualTo(StudentValidationMessage.PASSPORT_REQUIRED);
  }
}
