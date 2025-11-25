package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.dto.UsuarioDTO;
import com.heartz.heartz_api.dto.UsuarioPatchDTO;
import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return service.getAllUsuarios();
    }

    @GetMapping("/{rut}")
    public Usuario getUsuarioByRut(@PathVariable String rut) {
        return service.getUsuarioByRut(rut);
    }

    @GetMapping("/correo/{correo}")
    public Usuario getUsuarioByCorreo(@PathVariable String correo) {
        return service.getUsuarioByCorreo(correo);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UsuarioDTO dto) {
        try {
            return ResponseEntity.ok(service.crearUsuario(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{rut}")
    public Usuario updateUsuario(@PathVariable String rut, @RequestBody Usuario dto) {
        return service.updateUsuario(rut, dto);
    }

    @PatchMapping("/{rut}")
    public ResponseEntity<?> patchUsuario(
            @PathVariable String rut,
            @Valid @RequestBody UsuarioPatchDTO dto
    ) {
        try {
            return ResponseEntity.ok(service.patchUsuario(rut, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{rut}")
    public void deleteUsuarioByRut(@PathVariable String rut) {
        service.deleteUsuarioByRut(rut);
    }

    @DeleteMapping("/all")
    public void deleteAllUsuarios() {
        service.deleteAllUsuarios();
    }
}
