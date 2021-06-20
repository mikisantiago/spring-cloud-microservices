package com.mikisantiago.order.consumer.repository;

import org.springframework.data.repository.CrudRepository;

import com.mikisantiago.commons.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}