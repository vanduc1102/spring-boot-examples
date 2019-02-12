package com.example.rabbitmqexample.queue;

import com.example.rabbitmqexample.dto.NotificationRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {
  private Logger LOGGER = LoggerFactory.getLogger(getClass());

  private final RabbitTemplate rabbitTemplate;

  @Value("${fanout.exchange}")
  private String fanoutExchange;

  @Autowired
  public QueueProducer(RabbitTemplate rabbitTemplate) {
    super();
    this.rabbitTemplate = rabbitTemplate;
  }

  public void produce(NotificationRequestDTO notificationDTO) throws Exception {
    LOGGER.info("Storing notification...");
    rabbitTemplate.setExchange(fanoutExchange);
    rabbitTemplate.convertAndSend(new ObjectMapper().writeValueAsString(notificationDTO));
    LOGGER.info("Notification stored in queue sucessfully");
  }
}
