package com.heartz.heartz_api.dto;

import lombok.Data;
import java.util.List;

@Data
public class VentaDTO {
    private String rutUsuario;
    private Integer total;
    private List<DetalleVentaDTO> detalles;
}
