package com.mikisantiago.invoice.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mikisantiago.commons.entity.Invoice;
import com.mikisantiago.invoice.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = { "http://localhost:8090" })
public class InvoiceController {

	@Autowired
	private InvoiceService service;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Invoice invoice = service.findById(id);
		if (invoice == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(invoice);
	}

	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Invoice invoice, BindingResult result) {
		if (result.hasErrors()) {
			return this.validate(result);
		}
		invoice.setInvoiceDate(new Date());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(invoice));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	protected ResponseEntity<?> validate(BindingResult result) {
		List<String> errors = new ArrayList<>();
		result.getFieldErrors().forEach(e -> errors.add(e.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

}