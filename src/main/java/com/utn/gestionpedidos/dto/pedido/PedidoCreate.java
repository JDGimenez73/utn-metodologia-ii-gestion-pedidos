package com.utn.gestionpedidos.dto.pedido;

import com.utn.gestionpedidos.dto.detallePedido.DetallePedidoCreate;
import com.utn.gestionpedidos.enums.Estado;
import com.utn.gestionpedidos.enums.FormaPago;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record PedidoCreate(
        @NotNull(message = "La fecha es obligatoria") LocalDate fecha,
        @NotNull(message = "El estado es obligatorio") Estado estado,
        @NotNull(message = "La forma de pago es obligatoria") FormaPago formaPago,
        @NotNull(message = "El usuario es obligatorio") Long usuarioId,
        @NotEmpty(message = "El pedido debe incluir al menos un detalle")
        List<@Valid DetallePedidoCreate> detalles
) {
}
