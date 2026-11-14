package com.utn.gestionpedidos.dto.usuario;

import com.utn.gestionpedidos.enums.Rol;

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol
) {
}
