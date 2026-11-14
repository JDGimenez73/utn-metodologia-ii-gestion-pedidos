package com.utn.gestionpedidos.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaCreate(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La descripción es obligatoria") String descripcion
) {
}
