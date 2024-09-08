package com.skyapi.weatherforecast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI weatherServiceAPI() {  
        return new OpenAPI()
                .info(new Info()
                        .title("Weather Forecast API") 
                        .description("This is the REST API for Weather Forecast Service")  
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))  
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to the Weather Forecast API Service Wiki Documentation")  
                        .url("https://weatherforecast-dummy-url.com.docs"));  
    }
}		
