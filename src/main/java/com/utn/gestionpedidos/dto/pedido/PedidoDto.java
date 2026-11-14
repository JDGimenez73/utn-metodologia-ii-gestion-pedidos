package com.utn.gestionpedidos.dto.pedido;

import com.utn.gestionpedidos.dto.detallePedido.DetallePedidoDto;
import com.utn.gestionpedidos.enums.Estado;
import com.utn.gestionpedidos.enums.FormaPago;
import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Long usuarioId,
        List<DetallePedidoDto> detalles
) {
}
