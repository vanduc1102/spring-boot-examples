package com.example.rabbitmqexample.queue;

import com.example.rabbitmqexample.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {
  private Logger LOGGER = LoggerFactory.getLogger(getClass());
  @Autowired private StudentService studentService;

  public void receiveMessage(String message) {
    LOGGER.info("Received (String) " + message);
    processMessage(message);
  }

  public void receiveMessage(byte[] message) {
    String strMessage = new String(message);
    LOGGER.info("Received (No String) " + strMessage);
    processMessage(strMessage);
  }

  private void processMessage(String message) {
    LOGGER.info("Ready to consume.......", message);
    studentService.consume(message);
  }
}
