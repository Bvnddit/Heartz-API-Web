package com.heartz.heartz_api.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UsuarioPatchDTO {

    private String nombre;
    @Email
    private String correo;
    private String contrasena;
    private String rol;
}
