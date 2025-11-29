package com.heartz.heartz_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.heartz.heartz_api.dto.DetalleVentaDTO;
import com.heartz.heartz_api.dto.VentaDTO;
import com.heartz.heartz_api.model.DetalleVenta;
import com.heartz.heartz_api.model.Venta;
import com.heartz.heartz_api.model.Vinilo;
import com.heartz.heartz_api.repository.UsuarioRepository;
import com.heartz.heartz_api.repository.VentaRepository;
import com.heartz.heartz_api.repository.ViniloRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ViniloRepository viniloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public ResponseEntity<?> registrarVenta(VentaDTO ventaDTO) {

        Venta venta = Venta.builder()
                .usuario(usuarioRepository.findById(ventaDTO.getIdUsuario()).orElse(null))
                .fecha(LocalDateTime.now())
                .total(ventaDTO.getTotal())
                .direccion(ventaDTO.getDireccion())
                .departamento(ventaDTO.getDepartamento())
                .region(ventaDTO.getRegion())
                .comuna(ventaDTO.getComuna())
                .indicaciones(ventaDTO.getIndicaciones())
                .build();

        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()) {

            Vinilo vinilo = viniloRepository.findById(detalleDTO.getIdVinilo())
                    .orElse(null);
            if (vinilo == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Vinilo no encontrado: " + detalleDTO.getIdVinilo()));
            }

            if (vinilo.getStock() < detalleDTO.getCantidad()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Stock insuficiente para vinilo: " + vinilo.getNombre()));
            }

            vinilo.setStock(vinilo.getStock() - detalleDTO.getCantidad());
            viniloRepository.save(vinilo); // opcional dentro del transactional

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta); // relación bidireccional
            detalle.setVinilo(vinilo);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecio());

            detalles.add(detalle);
        }

        venta.setDetalles(detalles);
        ventaRepository.save(venta);
        return ResponseEntity.ok(Map.of("message", "Venta registrada exitosamente"));
    }

    public ResponseEntity<?> obtenerTodasLasVentas() {

        List<Venta> ventas = ventaRepository.findAllWithDetalles();

        List<Map<String, Object>> response = ventas.stream().map(v -> {
            Map<String, Object> mapVenta = new HashMap<>();
            mapVenta.put("idVenta", v.getIdVenta());
            mapVenta.put("fecha", v.getFecha());
            mapVenta.put("total", v.getTotal());
            mapVenta.put("direccion", v.getDireccion());
            mapVenta.put("departamento", v.getDepartamento());
            mapVenta.put("region", v.getRegion());
            mapVenta.put("comuna", v.getComuna());
            mapVenta.put("indicaciones", v.getIndicaciones());

            List<Map<String, Object>> mapDetalles = v.getDetalles().stream().map(d -> {
                Map<String, Object> mapDetalle = new HashMap<>();
                mapDetalle.put("idDetalle", d.getIdDetalle());
                mapDetalle.put("cantidad", d.getCantidad());
                mapDetalle.put("precioUnitario", d.getPrecioUnitario());

                Map<String, Object> mapVinilo = new HashMap<>();
                mapVinilo.put("idVinilo", d.getVinilo().getIdVin());
                mapVinilo.put("nombre", d.getVinilo().getNombre());
                mapVinilo.put("artista", d.getVinilo().getArtista());
                mapVinilo.put("precio", d.getVinilo().getPrecio());

                mapDetalle.put("vinilo", mapVinilo);
                return mapDetalle;
            }).toList();

            mapVenta.put("detalles", mapDetalles);

            return mapVenta;
        }).toList();

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> obtenerVentasPorUsuario(Long idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "El usuario con ID " + idUsuario + " no existe"));
        }

        List<Venta> ventas = ventaRepository.findByUsuarioWithDetalles(idUsuario);

        if (ventas.isEmpty()) {
            return ResponseEntity.ok(List.of()); // respuesta limpia []
        }

        List<Map<String, Object>> response = ventas.stream().map(v -> {
            Map<String, Object> mapVenta = new HashMap<>();
            mapVenta.put("idVenta", v.getIdVenta());
            mapVenta.put("fecha", v.getFecha());
            mapVenta.put("total", v.getTotal());
            mapVenta.put("direccion", v.getDireccion());
            mapVenta.put("departamento", v.getDepartamento());
            mapVenta.put("region", v.getRegion());
            mapVenta.put("comuna", v.getComuna());
            mapVenta.put("indicaciones", v.getIndicaciones());

            List<Map<String, Object>> detalles = v.getDetalles().stream().map(d -> {
                Map<String, Object> det = new HashMap<>();
                det.put("idDetalle", d.getIdDetalle());
                det.put("cantidad", d.getCantidad());
                det.put("precioUnitario", d.getPrecioUnitario());

                Map<String, Object> vinilo = new HashMap<>();
                vinilo.put("idVinilo", d.getVinilo().getIdVin());
                vinilo.put("nombre", d.getVinilo().getNombre());
                vinilo.put("artista", d.getVinilo().getArtista());
                vinilo.put("precio", d.getVinilo().getPrecio());

                det.put("vinilo", vinilo);
                return det;
            }).toList();

            mapVenta.put("detalles", detalles);
            return mapVenta;
        }).toList();

        return ResponseEntity.ok(response);
    }

}
