package com.heartz.heartz_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer viniloId;

    private String nombre;
    private Integer precio;
    private String img;

    private Integer cantidad = 1;
}
