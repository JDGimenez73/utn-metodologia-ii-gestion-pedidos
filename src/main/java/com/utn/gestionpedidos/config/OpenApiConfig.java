package com.utn.gestionpedidos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI gestionPedidosOpenApi() {
        return new OpenAPI().info(new Info()
                .title("API REST - Gestión de Pedidos")
                .description("Trabajo práctico de Programación IV")
                .version("1.0.0"));
    }
}
