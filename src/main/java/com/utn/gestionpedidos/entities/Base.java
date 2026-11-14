/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.gestionpedidos.entities;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
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
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@SuperBuilder
@MappedSuperclass
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected boolean eliminado;

    protected LocalDateTime createdAt;

    public Base(Long id) {
        this.id = id;
        this.eliminado = false;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void prePersist() {
        eliminado = false;
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
