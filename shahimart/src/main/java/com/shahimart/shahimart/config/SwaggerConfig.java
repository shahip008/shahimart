package com.shahimart.shahimart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ShahiMart API",
                version = "1.0",
                description = "E-commerce platform APIs",
                termsOfService = "https://shahimart.com/terms",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Support Team",
                        email = "support@shahimart.com"
                )
        )
)
public class SwaggerConfig {
}
