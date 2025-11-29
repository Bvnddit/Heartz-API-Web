package com.heartz.heartz_api.dto;

import lombok.Data;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para crear una nueva venta")
public class VentaDTO {
    private Long idUsuario;
    private Integer total;
    private List<DetalleVentaDTO> detalles;
    private String direccion;
    private String departamento;
    private String region;
    private String comuna;
    private String indicaciones;
}
