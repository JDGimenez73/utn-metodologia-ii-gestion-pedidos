package com.utn.gestionpedidos.dto.detallePedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallePedidoCreate(
        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor que cero") Integer cantidad,
        @NotNull(message = "El producto es obligatorio") Long productoId
) {
}
