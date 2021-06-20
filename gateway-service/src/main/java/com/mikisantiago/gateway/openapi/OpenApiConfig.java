package com.mikisantiago.gateway.openapi;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	@Bean
	public List<GroupedOpenApi> apis(SwaggerUiConfigParameters parameters, RouteDefinitionLocator locator) {
		List<GroupedOpenApi> groups = new ArrayList<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		assert definitions != null;
		definitions.forEach(definition -> System.out.println("id: " + definition.getId() + " " + definition.getUri().toString()));
		definitions.stream().filter(definition -> definition.getId().matches(".*-service")).forEach(definition -> {
			String name = definition.getId().replaceAll("-service", "");
			parameters.addGroup(name);
			GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
		});
		return groups;
	}

}