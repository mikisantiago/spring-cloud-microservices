package com.mikisantiago.order.producer.service;

import com.mikisantiago.commons.entity.Order;

public interface OrderService {

	String send(Order order);

}