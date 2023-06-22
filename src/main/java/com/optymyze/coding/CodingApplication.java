package com.optymyze.coding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class CodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodingApplication.class, args);
    }

    @Bean
    public Docket codingApplicationSwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.optymyze.coding")).build();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
