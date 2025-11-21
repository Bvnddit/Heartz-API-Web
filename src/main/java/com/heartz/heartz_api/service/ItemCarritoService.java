package com.heartz.heartz_api.service;

import com.heartz.heartz_api.model.ItemCarrito;
import com.heartz.heartz_api.model.Vinilo;
import com.heartz.heartz_api.repository.ItemCarritoRepository;
import com.heartz.heartz_api.repository.ViniloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCarritoService {

    @Autowired
    private ItemCarritoRepository carritoRepo;

    @Autowired
    private ViniloRepository viniloRepo;

    // OBTENER TODOS LOS ITEMS
    public List<ItemCarrito> getCarritoItems() {
        return carritoRepo.findAll();
    }

    // AGREGAR ITEM (suma si ya existe)
    public ItemCarrito addItemToCarrito(Integer viniloId) throws Exception {

        Vinilo v = viniloRepo.findById(viniloId).orElse(null);
        if (v == null) {
            throw new Exception("El vinilo no existe.");
        }

        // verificar si ya existe item
        List<ItemCarrito> carrito = carritoRepo.findAll();
        ItemCarrito existing = carrito.stream()
                .filter(i -> i.getViniloId().equals(viniloId))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setCantidad(existing.getCantidad() + 1);
            return carritoRepo.save(existing);
        }

        // Crear nuevo ítem
        ItemCarrito nuevo = new ItemCarrito(
                null,
                v.getIdVin(),
                v.getNombre(),
                v.getPrecio(),
                v.getImg(),
                1
        );

        return carritoRepo.save(nuevo);
    }

    // RESTAR ITEM (si llega a 0, eliminar)
    public void removeItemFromCarrito(Integer itemId) {

        ItemCarrito item = carritoRepo.findById(itemId).orElse(null);
        if (item == null) return;

        if (item.getCantidad() > 1) {
            item.setCantidad(item.getCantidad() - 1);
            carritoRepo.save(item);
        } else {
            carritoRepo.delete(item);
        }
    }

    // ACTUALIZAR CANTIDAD
    public ItemCarrito updateItemCarrito(ItemCarrito item) {
        return carritoRepo.save(item);
    }

    // ELIMINAR ITEM
    public void deleteItemCarrito(Integer id) {
        carritoRepo.deleteById(id);
    }

    // LIMPIAR CARRITO
    public void clearCarrito() {
        carritoRepo.deleteAll();
    }
}
