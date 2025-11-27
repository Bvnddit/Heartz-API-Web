package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.model.ItemCarrito;
import com.heartz.heartz_api.service.ItemCarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin("*")
@Tag(name = "Carrito", description = "Endpoints de carrito")
public class ItemCarritoController {

    @Autowired
    private ItemCarritoService service;


    @GetMapping
    @Operation(summary = "Obtener todos los items del carrito", description = "Obtener todos los items del carrito registrados en la base de datos")
    public List<ItemCarrito> getCarritoItems() {
        return service.getCarritoItems();
    }


    @PostMapping("/add/{viniloId}")
    @Operation(summary = "Agregar item al carrito", description = "Agregar un nuevo item al carrito")
    public ItemCarrito addItem(@PathVariable Integer viniloId) throws Exception {
        return service.addItemToCarrito(viniloId);
    }

    @PostMapping("/remove/{itemId}")
    @Operation(summary = "Remover item del carrito", description = "Remover un item del carrito")
    public void removeItem(@PathVariable Integer itemId) {
        service.removeItemFromCarrito(itemId);
    }


    @PutMapping("/update")
    @Operation(summary = "Actualizar item del carrito", description = "Actualizar un item del carrito")
    public ItemCarrito updateItem(@RequestBody ItemCarrito item) {
        return service.updateItemCarrito(item);
    }


    @DeleteMapping("/{id}") 
    @Operation(summary = "Eliminar item del carrito", description = "Eliminar un item del carrito")
    public void deleteItem(@PathVariable Integer id) {
        service.deleteItemCarrito(id);
    }


    @DeleteMapping("/clear")
    @Operation(summary = "Limpiar carrito completo", description = "Limpiar todos los items del carrito")
    public void clearCarrito() {
        service.clearCarrito();
    }
}
