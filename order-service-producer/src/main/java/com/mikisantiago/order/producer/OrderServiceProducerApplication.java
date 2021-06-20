package com.mikisantiago.order.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({ "com.mikisantiago.commons.entity" })
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class OrderServiceProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceProducerApplication.class, args);
	}

}