package com.gta.cars.config;

import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new io.swagger.v3.oas.models.security.SecurityScheme()
                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("GTA Cars")
                        .version("v1")
                        .description("API Rest para Gerenciamento de garagens e carros do GTA V Online")
                        .termsOfService("")
                        .license(new License().name("MIT License")
                                .url("https://github.com/GabryelMelo07/gta-cars/blob/master/LICENSE")));
    }

    @Bean
    @Primary
    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
        SwaggerUiConfigProperties swaggerUiConfig = new SwaggerUiConfigProperties();
        swaggerUiConfig.setDefaultModelExpandDepth(-1);
        swaggerUiConfig.setDocExpansion("none");
        return swaggerUiConfig;
    }

}
