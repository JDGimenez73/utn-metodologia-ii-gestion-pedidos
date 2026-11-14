package com.utn.gestionpedidos.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CategoriaEdit(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La descripción es obligatoria") String descripcion
) {
}
