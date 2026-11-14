/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.gestionpedidos.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
/**
 *
 * @author JDGim
 */
@Entity
@Getter
@Setter
@ToString(callSuper = true, exclude = "productos")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Categoria extends Base {

    @EqualsAndHashCode.Include
    private String nombre;

    private String descripcion;

    @Builder.Default
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private Set<Producto> productos = new HashSet<>();

    public Categoria(Long id, String nombre, String descripcion) {
        super(id, false, java.time.LocalDateTime.now());
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.setCategoria(this);
    }
}
