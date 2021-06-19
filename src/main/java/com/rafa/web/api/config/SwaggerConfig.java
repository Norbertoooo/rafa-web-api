package com.rafa.web.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // http://localhost:8081/v2/api-docs
    // http://localhost:8081/swagger-ui/index.html

    @Value("${server.port}")
    private String serverPort;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rafa.web.api.web"))
                .paths(PathSelectors.any())
                .build()
                .host("http://localhost:/" + serverPort + "/api")
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title ("Rafa Web Api")
                .description ("Rest api do projeto R.A.F.A")
                .version("1.0.0")
                .build();
    }
}
