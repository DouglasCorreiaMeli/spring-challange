package com.meli.socialMeli.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TITLE = "Social Meli";
    public static final String DESCRIPTION = "Social Meli API documentation";
    public static final String PACKAGE = "com.meli.socialMeli";
    public static final String VERSION = "0.1";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder() //
                .title(TITLE) //
                .description(DESCRIPTION) //
                .version(VERSION)
                .license("MIT License")//
                .licenseUrl("https://opensource.org/licenses/MIT")
                .termsOfServiceUrl("https://github.com/DouglasCorreiaBrito/JobTech.IO")
                .build();
    }
}
