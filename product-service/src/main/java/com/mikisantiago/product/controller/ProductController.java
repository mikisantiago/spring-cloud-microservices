package com.mikisantiago.product.controller;

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

import com.mikisantiago.commons.entity.Product;
import com.mikisantiago.product.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = { "http://localhost:8090" })
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Product product = service.findById(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(product);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Product product, BindingResult result) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		Product currentProduct = this.service.findById(id);
		if (currentProduct == null) {
			return ResponseEntity.noContent().build();
		}
		currentProduct.setName(product.getName());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setStock(product.getStock());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(currentProduct));
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