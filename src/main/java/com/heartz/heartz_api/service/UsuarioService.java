package com.heartz.heartz_api.service;

import com.heartz.heartz_api.dto.UsuarioDTO;
import com.heartz.heartz_api.dto.UsuarioPatchDTO;
import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    private static final List<String> ROLES_VALIDOS = List.of("Admin", "Empleado", "Cliente");

    // ---------------- CREAR ----------------
    public Usuario crearUsuario(UsuarioDTO dto) {

        if (usuarioRepo.existsById(dto.getRut())) {
            throw new RuntimeException("El RUT ya existe");
        }

        if (usuarioRepo.existsByCorreo(dto.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (!ROLES_VALIDOS.contains(dto.getRol())) {
            throw new RuntimeException("Rol inválido");
        }

        Usuario u = new Usuario(
                dto.getRut(),
                dto.getNombre(),
                dto.getCorreo(),
                dto.getContrasena(),
                dto.getRol()
        );

        return usuarioRepo.save(u);
    }

    // ---------------- GET ----------------
    public List<Usuario> getAllUsuarios() { return usuarioRepo.findAll(); }

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
        if (u == null) return null;

        u.setNombre(datos.getNombre());
        u.setCorreo(datos.getCorreo());
        u.setContrasena(datos.getContrasena());
        u.setRol(datos.getRol());

        return usuarioRepo.save(u);
    }

    // ---------------- PATCH PARCIAL ----------------
    public Usuario patchUsuario(String rut, UsuarioPatchDTO dto) {

        Usuario u = usuarioRepo.findById(rut).orElse(null);
        if (u == null) return null;

        if (dto.getNombre() != null) u.setNombre(dto.getNombre());
        if (dto.getCorreo() != null) {
            if (usuarioRepo.existsByCorreo(dto.getCorreo()))
                throw new RuntimeException("El correo ya está registrado");
            u.setCorreo(dto.getCorreo());
        }
        if (dto.getContrasena() != null) u.setContrasena(dto.getContrasena());
        if (dto.getRol() != null) {
            if (!ROLES_VALIDOS.contains(dto.getRol()))
                throw new RuntimeException("Rol inválido");
            u.setRol(dto.getRol());
        }

        return usuarioRepo.save(u);
    }

    // ---------------- ELIMINAR ----------------
    public void deleteUsuarioByRut(String rut) { usuarioRepo.deleteById(rut); }

    public void deleteAllUsuarios() { usuarioRepo.deleteAll(); }
}