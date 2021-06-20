package com.mikisantiago.order.producer.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikisantiago.commons.entity.Order;
import com.mikisantiago.order.producer.config.RabbitConfig;
import com.mikisantiago.order.producer.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RabbitTemplate template;

	@Override
	public String send(Order order) {
		template.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, order);
		log.info("Message sent {}", order);
		return "Sent!";
	}

}