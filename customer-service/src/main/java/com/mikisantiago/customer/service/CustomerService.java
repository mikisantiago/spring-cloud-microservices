package com.mikisantiago.customer.service;

import java.util.List;

import com.mikisantiago.commons.entity.Customer;

public interface CustomerService {

	List<Customer> findAll();

	Customer findById(Long id);

	Customer save(Customer customer);

	void deleteById(Long id);

}