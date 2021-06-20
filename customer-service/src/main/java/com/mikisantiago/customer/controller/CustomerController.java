package com.mikisantiago.customer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikisantiago.commons.entity.Customer;
import com.mikisantiago.customer.service.CustomerService;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = { "http://localhost:8090" })
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Customer customer = service.findById(id);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Customer customer, BindingResult result) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customer));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Customer customer, BindingResult result,
			@PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		Customer currentCustomer = this.service.findById(id);
		if (currentCustomer == null) {
			return ResponseEntity.noContent().build();
		}
		currentCustomer.setName(customer.getName());
		currentCustomer.setLastName(customer.getLastName());
		currentCustomer.setAddress(customer.getAddress());
		currentCustomer.setPhone(customer.getPhone());
		currentCustomer.setEmail(customer.getEmail());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(currentCustomer));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<?> validate(BindingResult result) {
		List<String> errors = new ArrayList<>();
		result.getFieldErrors().forEach(e -> errors.add(e.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

}