package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.model.Usuario;
import com.heartz.heartz_api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Usuario insertUsuario(@RequestBody Usuario usuario) {
        return service.insertUsuario(usuario);
    }

    @DeleteMapping("/all")
    public void deleteAllUsuarios() {
        service.deleteAllUsuarios();
    }
}
