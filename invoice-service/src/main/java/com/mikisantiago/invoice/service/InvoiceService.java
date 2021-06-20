package com.mikisantiago.invoice.service;

import java.util.List;

import com.mikisantiago.commons.entity.Invoice;

public interface InvoiceService {

	List<Invoice> findAll();

	Invoice findById(Long id);

	Invoice save(Invoice invoice);

	void deleteById(Long id);

}