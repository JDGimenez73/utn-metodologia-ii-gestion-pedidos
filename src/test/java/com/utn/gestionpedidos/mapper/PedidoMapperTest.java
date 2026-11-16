package com.utn.gestionpedidos.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.utn.gestionpedidos.dto.pedido.PedidoDto;
import com.utn.gestionpedidos.entities.DetallePedido;
import com.utn.gestionpedidos.entities.Pedido;
import com.utn.gestionpedidos.entities.Producto;
import com.utn.gestionpedidos.entities.Usuario;
import org.junit.jupiter.api.Test;

class PedidoMapperTest {

    private final PedidoMapper mapper = new PedidoMapper();

    @Test
    void conviertePedidoConDetalleADto() {
        Usuario usuario = Usuario.builder().build();
        usuario.setId(10L);

        Producto producto = Producto.builder()
                .nombre("Producto de prueba")
                .precio(1800.0)
                .build();
        producto.setId(20L);

        Pedido pedido = Pedido.builder()
                .usuario(usuario)
                .total(3600.0)
                .build();
        pedido.setId(30L);

        DetallePedido detalle = new DetallePedido(null, 2, producto);
        detalle.setId(40L);
        pedido.addDetallePedido(detalle);

        PedidoDto resultado = mapper.convertirADto(pedido);

        assertEquals(30L, resultado.id());
        assertEquals(10L, resultado.usuarioId());
        assertEquals(3600.0, resultado.total(), 0.001);
        assertEquals(1, resultado.detalles().size());

        var detalleDto = resultado.detalles().get(0);

        assertEquals(40L, detalleDto.id());
        assertEquals(2, detalleDto.cantidad());
        assertEquals(3600.0, detalleDto.subtotal(), 0.001);
        assertEquals(20L, detalleDto.productoId());
        assertEquals("Producto de prueba", detalleDto.productoNombre());
    }
}