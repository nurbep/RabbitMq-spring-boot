package com.example.RabbitMqprojectconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {
  private Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);


  @RabbitListener(queues =  {"producer-data"})
  public void recievedMessage(ProducerData  data) {
    System.out.println("Recieved Message From RabbitMQ: " + data.getName());

    logger.info("Recieved Message From RabbitMQ: " + data.getName());
  }
}