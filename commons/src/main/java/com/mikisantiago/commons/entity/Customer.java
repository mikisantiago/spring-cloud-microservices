package com.mikisantiago.commons.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = -3060986653470234751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Please provide a valid name.")
	private String name;

	@NotEmpty(message = "Please provide a valid last name.")
	@Column(name = "last_name")
	private String lastName;

	@NotEmpty(message = "Please provide a valid address.")
	private String address;

	@NotEmpty(message = "Please provide a valid phone.")
	private String phone;

	@NotEmpty(message = "Please provide a valid email.")
	@Email(message = "Please provide a valid email format.")
	private String email;

	@JsonProperty(access = Access.READ_ONLY)
	@JsonIgnoreProperties(value = { "customer", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Invoice> invoices;

}