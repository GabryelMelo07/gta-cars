package com.gta.cars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class SwaggerConfiguration {

        @Bean
        public OpenAPI customOpenApi() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("GTA Cars")
                                                .version("v1")
                                                .description("API Rest para Gerenciamento de garagens e carros do GTA V Online")
                                                .termsOfService("")
                                                .license(new License().name("Apache 2.0").url("")));
        }

}
