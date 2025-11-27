package com.heartz.heartz_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para crear un nuevo detalle de venta")
public class DetalleVentaDTO {
    private Integer idVinilo;
    private Integer cantidad;
    private Integer precio;
}
