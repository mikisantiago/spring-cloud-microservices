package com.mikisantiago.order.consumer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikisantiago.commons.entity.Order;
import com.mikisantiago.order.consumer.repository.OrderRepository;
import com.mikisantiago.order.consumer.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Override
	@Transactional
	public Order save(Order order) {
		return repository.save(order);
	}

}