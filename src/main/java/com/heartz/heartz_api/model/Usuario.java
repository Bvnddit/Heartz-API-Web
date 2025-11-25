package com.heartz.heartz_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private String rut;

    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

}
