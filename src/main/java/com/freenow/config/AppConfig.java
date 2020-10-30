package com.freenow.config;

import java.util.Collections;

import org.modelmapper.ModelMapper;
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
public class AppConfig
{

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)  
            .select()                                  
            .apis(RequestHandlerSelectors.basePackage("com.freenow.controller"))              
            .paths(PathSelectors.any())                          
            .build()
            .apiInfo(apiInfo()); 
    }
    
    
    private ApiInfo apiInfo() {
        return new ApiInfo("Car Management API", 
            "Free Now's Car Management API", 
            "v1",
            "termsOfServiceUrl", 
            new Contact("Shweta Goel", "https://github.com/Shwetagoel31", "shwetagoel31@gmail.com"),
            "license", 
            "licenseUrl",
            Collections.emptyList());
    }
    
}
