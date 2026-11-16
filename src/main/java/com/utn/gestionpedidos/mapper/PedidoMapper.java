package com.utn.gestionpedidos.mapper;

import com.utn.gestionpedidos.dto.detallePedido.DetallePedidoDto;
import com.utn.gestionpedidos.dto.pedido.PedidoDto;
import com.utn.gestionpedidos.entities.Pedido;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoDto convertirADto(Pedido pedido) {
        List<DetallePedidoDto> detalles = pedido.getDetalles().stream()
                .map(detalle -> new DetallePedidoDto(
                        detalle.getId(),
                        detalle.getCantidad(),
                        detalle.getSubtotal(),
                        detalle.getProducto().getId(),
                        detalle.getProducto().getNombre()))
                .toList();

        return new PedidoDto(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaPago(),
                pedido.getUsuario().getId(),
                detalles
        );
    }
}