package com.mikisantiago.invoice.repository;

import org.springframework.data.repository.CrudRepository;

import com.mikisantiago.commons.entity.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}