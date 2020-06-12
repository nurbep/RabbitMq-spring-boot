package com.example.RabbitMqprojectconsumer;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableRabbit
public class MessageQueueConfig {

  @Bean
  public RabbitTemplate rabbitTemplate(
      final ConnectionFactory connectionFactory,
      @Autowired Jackson2JsonMessageConverter producerJackson2MessageConverter) {

    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter);
    return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter(@Autowired ObjectMapper objectMapper) {
    return new Jackson2JsonMessageConverter(objectMapper);
  }

  @Bean
  public TopicExchange employeeExchange() {
    return new TopicExchange("producer-exchange");
  }

  @Bean
  public Queue updateEmployeeManagerQueue() {
    return new Queue("producer-data");
  }

 /* @Bean
  public Queue updateEmployeesProjectBoss() {
    return new Queue("employees-project-boss-update");
  }
*/

  @Bean
  public Binding declareBindingApp1() {
    return BindingBuilder.bind(updateEmployeeManagerQueue()).to(employeeExchange()).with("data-sent");
  }
  /*@Bean
  public List<Binding> employeeBindings() {
    List<Binding> bindings = new ArrayList<>();

    //add queue bindings to list
    bindings.add(BindingBuilder.bind(updateEmployeeManagerQueue()).to(employeeExchange()).with("data-sent"));
    //bindings.add(BindingBuilder.bind(updateEmployeesProjectBoss()).to(employeeExchange()).with("employees-project-boss-update"));

    return bindings;
  }*/
}

