package com.heartz.heartz_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.heartz.heartz_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    boolean existsByCorreo(String correo);
}

