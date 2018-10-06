package com.in28minutes.springboot.rest.example.dto;

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

  @NotNull(message = "400-001|Passport number is required.")
  @ApiModelProperty(example = "C12313123")
  private String passportNumber;

  @ApiModelProperty(example = "acbd@gmail.com")
  @Email(message = "400-002|Email is invalid.")
  private String email;

  @Pattern(
      regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
      message = "400-002|Phone number is invalid.")
  @ApiModelProperty(example = "0987654321")
  private String phoneNumber;
}
