package com.utn.gestionpedidos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/")
    public String inicio() {
        return "¡Gestión de pedidos funcionando correctamente!";
    }

    @GetMapping("/api/saludo")
    public String saludo() {
        return "¡Hola desde Spring Boot!";
    }
}

