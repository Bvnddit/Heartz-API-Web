package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.model.ItemCarrito;
import com.heartz.heartz_api.service.ItemCarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
@CrossOrigin("*")
public class ItemCarritoController {

    @Autowired
    private ItemCarritoService service;

    // Obtener todo el carrito
    @GetMapping
    public List<ItemCarrito> getCarritoItems() {
        return service.getCarritoItems();
    }

    // Agregar item al carrito
    @PostMapping("/add/{viniloId}")
    public ItemCarrito addItem(@PathVariable Integer viniloId) throws Exception {
        return service.addItemToCarrito(viniloId);
    }

    // Remover (restar) un item
    @PostMapping("/remove/{itemId}")
    public void removeItem(@PathVariable Integer itemId) {
        service.removeItemFromCarrito(itemId);
    }

    // Actualizar cantidad manualmente
    @PutMapping("/update")
    public ItemCarrito updateItem(@RequestBody ItemCarrito item) {
        return service.updateItemCarrito(item);
    }

    // Borrar item del carrito
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id) {
        service.deleteItemCarrito(id);
    }

    // Limpiar carrito completo
    @DeleteMapping("/clear")
    public void clearCarrito() {
        service.clearCarrito();
    }
}
