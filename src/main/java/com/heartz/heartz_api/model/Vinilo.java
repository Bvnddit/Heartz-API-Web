package com.heartz.heartz_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vinilos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vinilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVin;

    @Column(name = "nombre")
    private String nombre;
    private String artista;
    private String genero;

    private Integer anno;
    private Integer precio;

    private String formato;
    private String colorVinilo;

    private Integer stock;
    private String sello;
    private String pais;
    private String edicion;

    private String duracion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String img;

}
