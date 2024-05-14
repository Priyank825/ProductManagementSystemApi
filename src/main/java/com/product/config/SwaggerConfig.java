package com.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openApI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Product management API")
						.description("This is producr management system api")
						.version("1.0")
						.contact(new Contact().name("priyank").email("kanhapatel825@gmail.com").url("product.com"))
						.license(new License().name("Apache")));
						
	}

}
