package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.dto.VentaDTO;
import com.heartz.heartz_api.model.Venta;
import com.heartz.heartz_api.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
@Tag(name = "Ventas", description = "Endpoints de ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/registrarventa")
    @Operation(summary = "Crear venta", description = "Crear una nueva venta")
    public ResponseEntity<?> crearVenta(@RequestBody VentaDTO ventaDTO) {
        return ventaService.registrarVenta(ventaDTO);
    }

    // Todas las ventas → SOLO ADMIN
    @GetMapping("/obtenerventas")
    public ResponseEntity<?> obtenerTodasLasVentas() {
        return ResponseEntity.ok(ventaService.obtenerTodasLasVentas());
    }

    @GetMapping("/obtenerventasusuario/{idUsuario}")
    public ResponseEntity<?> obtenerVentasPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(ventaService.obtenerVentasPorUsuario(idUsuario));
    }

}
