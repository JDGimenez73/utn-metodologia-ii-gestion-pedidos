/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.gestionpedidos.entities;

import com.utn.gestionpedidos.enums.Estado;
import com.utn.gestionpedidos.enums.FormaPago;
import com.utn.gestionpedidos.interfaces.Calculable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Pedido extends Base implements Calculable {

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private Double total;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Builder.Default
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private Set<DetallePedido> detalles = new HashSet<>();

    public Pedido(Long id, LocalDate fecha, Estado estado, FormaPago formaPago) {
        super(id);
        this.fecha = fecha;
        this.estado = estado;
        this.formaPago = formaPago;
        this.total = 0.0;
    }

    public void addDetallePedido(DetallePedido detalle) {
        detalles.add(detalle);
        detalle.setPedido(this);
        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        return detalles.stream()
                .filter(detalle -> detalle.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        detalles.removeIf(detalle -> detalle.getProducto().equals(producto));
        calcularTotal();
    }

    @Override
    public void calcularTotal() {
        total = detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", total=" + total +
                ", formaPago=" + formaPago +
                ", detalles=" + detalles +
                '}';
    }
}
