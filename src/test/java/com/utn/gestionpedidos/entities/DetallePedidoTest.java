package com.utn.gestionpedidos.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DetallePedidoTest {

    @Test
    void calculaSubtotalParaUnaUnidad() {
        Producto producto = Producto.builder()
                .precio(1500.0)
                .build();

        DetallePedido detalle = new DetallePedido(null, 1, producto);

        assertEquals(1500.0, detalle.getSubtotal(), 0.001);
    }

    @Test
    void calculaSubtotalParaVariasUnidades() {
        Producto producto = Producto.builder()
                .precio(1800.0)
                .build();

        DetallePedido detalle = new DetallePedido(null, 3, producto);

        assertEquals(5400.0, detalle.getSubtotal(), 0.001);
    }

    @Test
    void calculaSubtotalParaCantidadCero() {
        Producto producto = Producto.builder()
                .precio(2000.0)
                .build();

        DetallePedido detalle = new DetallePedido(null, 0, producto);

        assertEquals(0.0, detalle.getSubtotal(), 0.001);
    }
}