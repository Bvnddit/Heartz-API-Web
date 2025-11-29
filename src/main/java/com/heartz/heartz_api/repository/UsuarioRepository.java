package com.heartz.heartz_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.heartz.heartz_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByRut(String rut);

}
