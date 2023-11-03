package com.TddSportsApp;

// import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@OpenAPIDefinition
public class TddSportsApp {

    public static void main(String[] args) {
        SpringApplication.run(TddSportsApp.class, args);
    }

    @Bean
    public OpenAPI tddSportsAppOpenAPI() {
        return new OpenAPI().info(new Info().title("TDD Sports App - Backend API")
                .description("TDD Sports App API reference for developers")
                .version("0.0.1"));
    }

}
