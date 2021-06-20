package com.mikisantiago.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikisantiago.commons.entity.Customer;
import com.mikisantiago.customer.repository.CustomerRepository;
import com.mikisantiago.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return (List<Customer>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Customer save(Customer customer) {
		return repository.save(customer);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}