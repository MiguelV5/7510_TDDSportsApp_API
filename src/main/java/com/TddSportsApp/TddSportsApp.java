package com.TddSportsApp;

// import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@OpenAPIDefinition
@RestController
public class TddSportsApp {

    public static void main(String[] args) {
        SpringApplication.run(TddSportsApp.class, args);
    }

    // ========================================

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    // ========================================

    @Bean
    public OpenAPI tddSportsAppOpenAPI() {
        return new OpenAPI().info(new Info().title("TDD Sports App API")
                .description("TDD Sports App API reference for developers")
                .version("0.0.1"));
    }

}
