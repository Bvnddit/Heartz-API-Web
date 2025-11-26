package com.heartz.heartz_api.service;

import com.heartz.heartz_api.dto.DetalleVentaDTO;
import com.heartz.heartz_api.dto.VentaDTO;
import com.heartz.heartz_api.model.DetalleVenta;
import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.model.Venta;
import com.heartz.heartz_api.model.Vinilo;
import com.heartz.heartz_api.repository.UsuarioRepository;
import com.heartz.heartz_api.repository.VentaRepository;
import com.heartz.heartz_api.repository.ViniloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ViniloRepository viniloRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Venta registrarVenta(VentaDTO ventaDTO) {

        Venta venta = new Venta();
        venta.setFecha(LocalDateTime.now());
        venta.setTotal(ventaDTO.getTotal());

        if (ventaDTO.getRutUsuario() != null && !ventaDTO.getRutUsuario().isEmpty()) {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(ventaDTO.getRutUsuario());
            usuarioOpt.ifPresent(venta::setUsuario);
        }

        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetalles()) {

            Vinilo vinilo = viniloRepository.findById(detalleDTO.getIdVinilo())
                    .orElseThrow(() -> new RuntimeException("Vinilo no encontrado: " + detalleDTO.getIdVinilo()));

            if (vinilo.getStock() < detalleDTO.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para vinilo: " + vinilo.getNombre());
            }

            vinilo.setStock(vinilo.getStock() - detalleDTO.getCantidad());
            viniloRepository.save(vinilo);

            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setVinilo(vinilo);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecio());

            detalles.add(detalle);
        }

        venta.setDetalles(detalles);

        return ventaRepository.save(venta);
    }
}
