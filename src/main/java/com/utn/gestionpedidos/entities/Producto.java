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
import lombok.ToString;
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
public class Producto extends Base {

    @EqualsAndHashCode.Include
    private String nombre;

    private Double precio;
    private String descripcion;
    private Integer stock;
    private String imagen;
    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @ToString.Exclude
    private Categoria categoria;

    public Producto(Long id, String nombre, Double precio, String descripcion,
                    Integer stock, String imagen, Boolean disponible, Categoria categoria) {
        super(id);
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.imagen = imagen;
        this.disponible = disponible;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", disponible=" + disponible +
                ", categoria=" + (categoria != null ? categoria.getNombre() : "Sin categoria") +
                '}';
    }
}
