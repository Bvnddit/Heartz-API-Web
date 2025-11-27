package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.model.Vinilo;
import com.heartz.heartz_api.service.ViniloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vinilos")
@CrossOrigin("*")
@Tag(name = "Vinilos", description = "Endpoints de vinilos")
public class ViniloController {

    @Autowired
    private ViniloService service;

    @GetMapping
    @Operation(summary = "Obtener todos los vinilos", description = "Obtener todos los vinilos registrados en la base de datos")
    public List<Vinilo> getAllVinilos() {
        return service.getAllVinilos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener vinilo por ID", description = "Obtener un vinilo específico por su ID")
    public Vinilo getViniloById(@PathVariable Integer id) {
        return service.getViniloById(id);
    }

    @PostMapping
    @Operation(summary = "Crear vinilo", description = "Crear un nuevo vinilo")
    public Vinilo insertVinilo(@RequestBody Vinilo v) {
        return service.insertVinilo(v);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar vinilo", description = "Actualizar un vinilo específico por su ID")
    public Vinilo updateVinilo(@PathVariable Integer id, @RequestBody Vinilo v) {
        v.setIdVin(id);
        return service.updateVinilo(v);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar vinilo", description = "Eliminar un vinilo específico por su ID")
    public void deleteVinilo(@PathVariable Integer id) {
        service.deleteVinilo(id);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Eliminar todos los vinilos", description = "Eliminar todos los vinilos registrados en la base de datos")
    public void deleteAllVinilos() {
        service.deleteAllVinilos();
    }
}
