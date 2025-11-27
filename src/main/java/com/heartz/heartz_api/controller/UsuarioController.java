package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.dto.UsuarioDTO;
import com.heartz.heartz_api.dto.UsuarioPatchDTO;
import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@Tag(name = "Usuarios", description = "Endpoints de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Obtener todos los usuarios registrados en la base de datos")
    public List<Usuario> getAllUsuarios() {
        return service.getAllUsuarios();
    }

    @GetMapping("/{rut}")
    @Operation(summary = "Obtener usuario por RUT", description = "Obtener un usuario específico por su RUT")
    public Usuario getUsuarioByRut(@PathVariable String rut) {
        return service.getUsuarioByRut(rut);
    }

    @GetMapping("/correo/{correo}")
    @Operation(summary = "Obtener usuario por correo", description = "Obtener un usuario específico por su correo")
    public Usuario getUsuarioByCorreo(@PathVariable String correo) {
        return service.getUsuarioByCorreo(correo);
    }

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario", description = "Registrar un nuevo usuario en la base de datos")
    public ResponseEntity<?> createUser(@RequestBody UsuarioDTO dto) {
        try {
            // Forzar el rol CLIENTE para registro público (seguridad)
            dto.setRol("CLIENTE");
            return ResponseEntity.ok(service.crearUsuario(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{rut}")
    @Operation(summary = "Actualizar usuario", description = "Actualizar un usuario específico por su RUT")
    public Usuario updateUsuario(@PathVariable String rut, @RequestBody Usuario dto) {
        return service.updateUsuario(rut, dto);
    }

    @PatchMapping("/{rut}")
    @Operation(summary = "Actualizar usuario parcialmente", description = "Actualizar un usuario específico por su RUT de forma parcial")
    public ResponseEntity<?> patchUsuario(
            @PathVariable String rut,
            @Valid @RequestBody UsuarioPatchDTO dto) {
        try {
            return ResponseEntity.ok(service.patchUsuario(rut, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{rut}")
    @Operation(summary = "Eliminar usuario", description = "Eliminar un usuario específico por su RUT")
    public void deleteUsuarioByRut(@PathVariable String rut) {
        service.deleteUsuarioByRut(rut);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Eliminar todos los usuarios", description = "Eliminar todos los usuarios registrados en la base de datos")
    public void deleteAllUsuarios() {
        service.deleteAllUsuarios();
    }
}
