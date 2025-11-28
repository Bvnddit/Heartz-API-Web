package com.heartz.heartz_api.service;

import com.heartz.heartz_api.dto.UsuarioDTO;
import com.heartz.heartz_api.dto.UsuarioPatchDTO;
import com.heartz.heartz_api.model.Rol;
import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.repository.RolRepository;
import com.heartz.heartz_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------------- CREAR ----------------
    public ResponseEntity<?> crearUsuario(UsuarioDTO dto) {

        if (usuarioRepo.existsById(dto.getRut())) {
            return ResponseEntity.badRequest().body(Map.of("message", "El RUT ya existe"));
        }

        if (usuarioRepo.existsByCorreo(dto.getCorreo())) {
            return ResponseEntity.badRequest().body(Map.of("message", "El correo ya está registrado"));
        }

        Rol rol = rolRepository.findByNombre(dto.getRol());

        Usuario u = Usuario.builder()
                .rut(dto.getRut())
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .contrasena(passwordEncoder.encode(dto.getContrasena()))
                .rol(rol)
                .build();

        usuarioRepo.save(u);
        return ResponseEntity.ok(Map.of("message", "Usuario creado exitosamente"));
    }

    // ---------------- GET ----------------
    public List<Usuario> getAllUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario getUsuarioByRut(String rut) {
        return usuarioRepo.findById(rut).orElse(null);
    }

    public Usuario getUsuarioByCorreo(String correo) {
        return usuarioRepo.findAll().stream()
                .filter(u -> u.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    // ---------------- UPDATE COMPLETO ----------------
    public Usuario updateUsuario(String rut, Usuario datos) {

        Usuario u = usuarioRepo.findById(rut).orElse(null);
        if (u == null)
            return null;

        u.setNombre(datos.getNombre());
        u.setCorreo(datos.getCorreo());
        u.setContrasena(datos.getContrasena());
        u.setRol(datos.getRol());

        return usuarioRepo.save(u);
    }

    // ---------------- PATCH PARCIAL ----------------
    public Usuario patchUsuario(String rut, UsuarioPatchDTO dto) {

        Usuario u = usuarioRepo.findById(rut).orElse(null);
        if (u == null)
            return null;

        if (dto.getNombre() != null)
            u.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) {
            if (usuarioRepo.existsByCorreo(dto.getCorreo()))
                throw new RuntimeException("El correo ya está registrado");
            u.setCorreo(dto.getCorreo());
        }
        if (dto.getContrasena() != null)
            u.setContrasena(dto.getContrasena());
        if (dto.getRol() != null) {
            u.setRol(dto.getRol());
        }

        return usuarioRepo.save(u);
    }

    // ---------------- ELIMINAR ----------------
    public void deleteUsuarioByRut(String rut) {
        usuarioRepo.deleteById(rut);
    }

    public void deleteAllUsuarios() {
        usuarioRepo.deleteAll();
    }
}