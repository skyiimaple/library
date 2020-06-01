package com.chuanshuke.manage_cms.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chuanshuke.manage_cms.ManageCmsApplication;

@SpringBootTest(classes = ManageCmsApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitmq {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Test
	public void sendMessage() {
		String exchange = "test_exchange";
		String routingKey = "test_routingKey";
		String message = "hello rabbitmq";
		rabbitTemplate.convertAndSend(exchange,routingKey, message);
		
	}
	
}
