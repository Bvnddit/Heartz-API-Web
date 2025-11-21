package com.heartz.heartz_api.service;

import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // INSERTAR
    public Usuario insertUsuario(Usuario u) {
        return usuarioRepo.save(u);
    }

    // OBTENER TODOS
    public List<Usuario> getAllUsuarios() {
        return usuarioRepo.findAll();
    }

    // OBTENER POR RUT
    public Usuario getUsuarioByRut(String rut) {
        return usuarioRepo.findById(rut).orElse(null);
    }

    // OBTENER POR CORREO
    public Usuario getUsuarioByCorreo(String correo) {
        return usuarioRepo.findAll().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    // ELIMINAR TODOS
    public void deleteAllUsuarios() {
        usuarioRepo.deleteAll();
    }
}
