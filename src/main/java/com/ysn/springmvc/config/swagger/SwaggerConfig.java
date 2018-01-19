package com.ysn.springmvc.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ysn.springmvc.controller.location"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring 4 RESTful Project Sample", // title
                "Documentation of Project REST API", // sub-title
                "1.0", // api version
                "Terms of service by YSN Studio", // term of service
                new Contact("Yudi Setiawan", "https://github.com/CoderJava", "kolonel.yudisetiawan@gmail.com"), // author
                "License of API is MIT", // Licence Type
                "-"
        );
        return apiInfo;
    }

}
