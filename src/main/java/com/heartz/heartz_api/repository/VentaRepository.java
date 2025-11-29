package com.heartz.heartz_api.repository;

import com.heartz.heartz_api.model.Venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query("SELECT v FROM Venta v " +
            "LEFT JOIN FETCH v.detalles d " +
            "LEFT JOIN FETCH d.vinilo")
    List<Venta> findAllWithDetalles();

    @Query("SELECT v FROM Venta v " +
       "LEFT JOIN FETCH v.detalles d " +
       "LEFT JOIN FETCH d.vinilo " +
       "WHERE v.usuario.idUsuario = :idUsuario")
List<Venta> findByUsuarioWithDetalles(Long idUsuario);


}
