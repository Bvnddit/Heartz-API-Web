package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.dto.UpdateUsuarioDTO;
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
        // test
        return service.getAllUsuarios();
    }

    @GetMapping("/{idUsuario}")
    @Operation(summary = "Obtener usuario por ID", description = "Obtener un usuario específico por su ID")
    public Usuario getUsuarioById(@PathVariable Long idUsuario) {
        return service.getUsuarioById(idUsuario);
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
            return service.crearUsuario(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{idUsuario}")
    @Operation(summary = "Actualizar usuario", description = "Actualizar un usuario específico por su ID")
    public ResponseEntity<?> updateUsuario(@PathVariable Long idUsuario, @RequestBody UpdateUsuarioDTO dto) {
        return service.updateUsuario(idUsuario, dto);
    }

    @PatchMapping("/{idUsuario}")
    @Operation(summary = "Actualizar usuario parcialmente", description = "Actualizar un usuario específico por su ID de forma parcial")
    public ResponseEntity<?> patchUsuario(
            @PathVariable Long idUsuario,
            @Valid @RequestBody UsuarioPatchDTO dto) {
        try {
            return ResponseEntity.ok(service.patchUsuario(idUsuario, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idUsuario}")
    @Operation(summary = "Eliminar usuario", description = "Eliminar un usuario específico por su ID")
    public void deleteUsuarioById(@PathVariable Long idUsuario) {
        service.deleteUsuarioById(idUsuario);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Eliminar todos los usuarios", description = "Eliminar todos los usuarios registrados en la base de datos")
    public void deleteAllUsuarios() {
        service.deleteAllUsuarios();
    }
}
