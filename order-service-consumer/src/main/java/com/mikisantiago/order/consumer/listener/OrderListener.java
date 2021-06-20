package com.mikisantiago.order.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mikisantiago.commons.entity.Order;
import com.mikisantiago.order.consumer.config.RabbitConfig;
import com.mikisantiago.order.consumer.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderListener {

	@Autowired
	private OrderService service;

	@RabbitListener(queues = RabbitConfig.QUEUE)
	public void receive(Order order) {
		log.info("Message received {}", order);
		Order responseId = service.save(order);
		log.info("Order saved with id {}", responseId.getId());
	}

}