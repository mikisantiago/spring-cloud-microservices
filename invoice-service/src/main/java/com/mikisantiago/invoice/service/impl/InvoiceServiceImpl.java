package com.mikisantiago.invoice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mikisantiago.commons.entity.Invoice;
import com.mikisantiago.invoice.repository.InvoiceRepository;
import com.mikisantiago.invoice.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Invoice> findAll() {
		return (List<Invoice>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Invoice findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Invoice save(Invoice invoice) {
		return repository.save(invoice);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}