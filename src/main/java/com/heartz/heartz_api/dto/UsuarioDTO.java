package com.heartz.heartz_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank(message = "El rut es obligatorio")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo no es válido")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;
}