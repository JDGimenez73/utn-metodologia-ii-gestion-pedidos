/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.gestionpedidos.entities;

import com.utn.gestionpedidos.enums.Rol;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
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
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder
public class Usuario extends Base {

    private String nombre;
    private String apellido;

    @EqualsAndHashCode.Include
    private String mail;

    private String celular;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Pedido> pedidos = new HashSet<>();

    public Usuario(Long id, String nombre, String apellido, String mail,
                   String celular, String contrasena, Rol rol) {
        super(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setUsuario(this);
    }

    public int cantidadPedidos() {
        return pedidos.size();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", celular='" + celular + '\'' +
                ", rol=" + rol +
                '}';
    }
}
