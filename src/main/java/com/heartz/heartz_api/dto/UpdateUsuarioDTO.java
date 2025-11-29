package com.heartz.heartz_api.dto;

import lombok.Data;

@Data
public class UpdateUsuarioDTO {

    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

}
