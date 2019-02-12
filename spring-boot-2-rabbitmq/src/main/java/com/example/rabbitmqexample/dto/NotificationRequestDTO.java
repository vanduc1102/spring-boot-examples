package com.example.rabbitmqexample.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationRequestDTO {
  @NotBlank private String name;
  private String content;
}
