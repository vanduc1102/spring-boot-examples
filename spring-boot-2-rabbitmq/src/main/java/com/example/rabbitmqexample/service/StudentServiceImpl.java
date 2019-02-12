package com.example.rabbitmqexample.service;

import com.example.rabbitmqexample.dto.NotificationRequestDTO;
import com.example.rabbitmqexample.queue.QueueProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
  private Logger LOGGER = LoggerFactory.getLogger(getClass());
  @Autowired private QueueProducer queueProducer;

  @Override
  public void produce(NotificationRequestDTO notificationRequest) throws Exception {
    queueProducer.produce(notificationRequest);
  }

  @Override
  public void consume(String notificationRequest) {
    LOGGER.info("Consume the studentServiceImple: " + notificationRequest.toString());
  }
}
