package com.epam.homework3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api-V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.epam.homework3.controller"))
                .paths(PathSelectors.ant("/api/v1/**")).build();
    }
}
