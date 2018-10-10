package com.in28minutes.springboot.rest.example.dto;

import static com.in28minutes.springboot.rest.example.constant.StudentValidationMessage.EMAIL_VALIDATION;
import static com.in28minutes.springboot.rest.example.constant.StudentValidationMessage.PASSPORT_REQUIRED;
import static com.in28minutes.springboot.rest.example.constant.StudentValidationMessage.PHONE_NUMBER_VALIDATION;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

  @ApiModelProperty(hidden = true)
  private Long id;

  @ApiModelProperty(example = "vanduc1102")
  private String username;

  @ApiModelProperty(example = "Donald Duck")
  private String displayName;

  @NotNull(message = PASSPORT_REQUIRED)
  @ApiModelProperty(example = "C12313123")
  private String passportNumber;

  @ApiModelProperty(example = "acbd@gmail.com")
  @Email(message = EMAIL_VALIDATION)
  private String email;

  @Pattern(
      regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
      message = PHONE_NUMBER_VALIDATION)
  @ApiModelProperty(example = "0987654321")
  private String phoneNumber;
}
