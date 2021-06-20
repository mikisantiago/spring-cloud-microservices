package com.mikisantiago.customer.repository;

import org.springframework.data.repository.CrudRepository;

import com.mikisantiago.commons.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}