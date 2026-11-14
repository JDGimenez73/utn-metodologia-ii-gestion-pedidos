package com.utn.gestionpedidos.dto.producto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoEdit(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero") Double precio,
        String descripcion,
        @NotNull(message = "El stock es obligatorio")
        @Min(value = 0, message = "El stock no puede ser negativo") Integer stock,
        String imagen,
        @NotNull(message = "La disponibilidad es obligatoria") Boolean disponible,
        @NotNull(message = "La categoría es obligatoria") Long categoriaId
) {
}
