package com.mikisantiago.invoice.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI info() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Invoice Service")
						.description("Invoice Service API Documentation")
						.version("1.0"));
	}

}