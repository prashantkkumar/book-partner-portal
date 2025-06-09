package org.example.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Book Partner Portal",
                version = "1.0",
                description = "API documentation for my monolithic Book Partner Portal Spring Boot application"
        )
)
public class SwaggerConfig {
}
