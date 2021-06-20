package com.mikisantiago.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikisantiago.commons.entity.Product;
import com.mikisantiago.product.repository.ProductRepository;
import com.mikisantiago.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return repository.save(product);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}