package com.mikisantiago.order.producer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikisantiago.commons.entity.Order;
import com.mikisantiago.order.producer.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	public ResponseEntity<?> send(@Valid @RequestBody Order order, BindingResult result) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setOrderDate(new Date());
		return ResponseEntity.status(HttpStatus.OK).body(service.send(order));
	}

	public ResponseEntity<?> validate(BindingResult result) {
		List<String> errors = new ArrayList<>();
		result.getFieldErrors().forEach(e -> errors.add(e.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

}