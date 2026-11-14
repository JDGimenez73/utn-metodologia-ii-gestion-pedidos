/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.gestionpedidos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author JDGim
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder
public class DetallePedido extends Base {

    private Integer cantidad;

    private Double subtotal;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public DetallePedido(Long id, Integer cantidad, Producto producto) {
        super(id);
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = cantidad * producto.getPrecio();
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "producto=" + (producto != null ? producto.getNombre() : "Sin producto") +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
