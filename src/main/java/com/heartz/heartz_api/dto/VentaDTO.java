package com.heartz.heartz_api.dto;

import lombok.Data;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para crear una nueva venta")
public class VentaDTO {
    @Schema(description = "RUT del usuario que realiza la venta")
    private String rutUsuario;
    private Integer total;
    private List<DetalleVentaDTO> detalles;
}
