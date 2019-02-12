package com.example.rabbitmqexample.controller;

import com.example.rabbitmqexample.dto.NotificationRequestDTO;
import com.example.rabbitmqexample.service.StudentService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/notifications")
public class MessageController {

  @Autowired private StudentService studentService;

  @PostMapping
  public ResponseEntity<NotificationRequestDTO> proceduceNotification(
      @RequestBody @Valid NotificationRequestDTO notification) throws Exception {
    studentService.produce(notification);
    return ResponseEntity.ok(notification);
  }
}
