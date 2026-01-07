package com.library.management.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Sistem de Management al Bibliotecii",
                version = "1.0",
                description = "API pentru gestionarea utilizatorilor, cartilor, autorilor, categoriilor si imprumuturilor."
        )
)
public class OpenApiConfig {
}


