package br.ce.crateus.fpo.vendas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Vendas API - SOLID")
                        .version("1.0.0")
                        .description("API REST para gestão de vendas, clientes, produtos e estoque.\n\n" +
                                "Implementa os 5 princípios SOLID com Spring Boot e PostgreSQL.\n\n" +
                                "Documentação interativa disponível via Swagger UI.")
                        .contact(new Contact()
                                .name("Francisco J S Martins")
                                .email("jandermartins@alu.ufc.br")
                                .url("https://github.com/jandermartins"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")));
    }
}
