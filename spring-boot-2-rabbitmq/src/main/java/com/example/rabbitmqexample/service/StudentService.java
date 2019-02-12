package com.example.rabbitmqexample.service;

import com.example.rabbitmqexample.dto.NotificationRequestDTO;

public interface StudentService {
  void produce(NotificationRequestDTO notificationRequest) throws Exception;

  void consume(String notificationRequest);
}
