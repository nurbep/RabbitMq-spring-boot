package com.example.RabbitMqprojectproducer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  private static final String EMPLOYEE_API_EXCHANGE = "producer-exchange";
  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public ApiController(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @GetMapping("/")
  public String sendData() {
    String name = "Nuruzzaman Bepari";

    ProducerData producerData = new ProducerData();
    producerData.setName(name);
    rabbitTemplate.convertAndSend(EMPLOYEE_API_EXCHANGE, "data-sent", producerData);
    return "Data has been sent successfully.";
  }
}
