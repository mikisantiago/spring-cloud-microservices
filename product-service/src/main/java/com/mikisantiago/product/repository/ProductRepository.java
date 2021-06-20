package com.mikisantiago.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.mikisantiago.commons.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}