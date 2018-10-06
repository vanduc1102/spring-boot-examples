package com.in28minutes.springboot.rest.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "STUDENT")
public class StudentEntity {

  @Id @GeneratedValue private Long id;
  private String name;
  private String passportNumber;
  private String email;
  private String phoneNumber;

  @Column(columnDefinition = "TEXT")
  private String github;
}
