package com.mycompany.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.info.Info;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Spring Boot 3 API",
        version = "1.0.0",
        description = "API Documentation"
    )
)
public class OpenApiConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("http://localhost:8080"))
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("My API")
                .version("1.0")
                .description("Spring Boot 3 + OpenAPI")
            );
    }

}
