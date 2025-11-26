package com.heartz.heartz_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heartz.heartz_api.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

    Rol findByNombre(String nombre);

}
