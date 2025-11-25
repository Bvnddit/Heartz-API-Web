package com.heartz.heartz_api.service;

import com.heartz.heartz_api.model.Vinilo;
import com.heartz.heartz_api.repository.ViniloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViniloService {

    @Autowired
    private ViniloRepository viniloRepo;

    // INSERTAR
    public Vinilo insertVinilo(Vinilo vinilo) {
        return viniloRepo.save(vinilo);
    }

    // ACTUALIZAR
    public Vinilo updateVinilo(Vinilo vinilo) {
        return viniloRepo.save(vinilo);
    }

    // ELIMINAR
    public void deleteVinilo(Integer id) {
        viniloRepo.deleteById(id);
    }

    // OBTENER TODOS
    public List<Vinilo> getAllVinilos() {
        return viniloRepo.findAll();
    }

    // OBTENER POR ID
    public Vinilo getViniloById(Integer id) {
        return viniloRepo.findById(id).orElse(null);
    }

    // ELIMINAR TODOS
    public void deleteAllVinilos() {
        viniloRepo.deleteAll();
    }
}
