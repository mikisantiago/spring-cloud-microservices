package com.mikisantiago.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = 9018830770410614346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Please provide a valid name.")
	private String name;

	@NotNull(message = "Please provide a valid price.")
	private BigDecimal price;

	@NotNull(message = "Please provide a valid stock.")
	private Integer stock;

}