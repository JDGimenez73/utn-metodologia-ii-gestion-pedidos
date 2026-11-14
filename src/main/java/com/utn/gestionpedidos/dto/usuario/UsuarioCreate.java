package com.utn.gestionpedidos.dto.usuario;

import com.utn.gestionpedidos.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioCreate(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El apellido es obligatorio") String apellido,
        @NotBlank(message = "El correo es obligatorio") @Email(message = "El correo no es válido") String mail,
        String celular,
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 4, message = "La contraseña debe tener al menos 4 caracteres") String contrasena,
        @NotNull(message = "El rol es obligatorio") Rol rol
) {
}
