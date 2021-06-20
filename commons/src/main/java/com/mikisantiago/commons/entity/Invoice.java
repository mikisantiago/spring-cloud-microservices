package com.mikisantiago.commons.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "invoices")
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1160984850801314444L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = Access.READ_ONLY)
	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date")
	private Date invoiceDate;

	@NotNull(message = "Please select a valid customer.")
	@JsonIgnoreProperties(value = { "invoices", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@NotEmpty(message = "Please add a valid product.")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id")
	private List<Item> items;

}