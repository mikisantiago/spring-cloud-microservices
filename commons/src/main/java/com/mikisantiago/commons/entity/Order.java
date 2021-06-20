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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = -7261468312454330494L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "order_number")
	private String orderNumber;

	@JsonProperty(access = Access.READ_ONLY)
	@Temporal(TemporalType.DATE)
	@Column(name = "order_date")
	private Date orderDate;

	@NotEmpty(message = "Please add a valid product.")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<Item> items;

}