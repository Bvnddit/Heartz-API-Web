package com.heartz.heartz_api.controller;

import com.heartz.heartz_api.model.Vinilo;
import com.heartz.heartz_api.service.ViniloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vinilos")
@CrossOrigin("*")
public class ViniloController {

    @Autowired
    private ViniloService service;

    @GetMapping
    public List<Vinilo> getAllVinilos() {
        return service.getAllVinilos();
    }

    @GetMapping("/{id}")
    public Vinilo getViniloById(@PathVariable Integer id) {
        return service.getViniloById(id);
    }

    @PostMapping
    public Vinilo insertVinilo(@RequestBody Vinilo v) {
        return service.insertVinilo(v);
    }

    @PutMapping("/{id}")
    public Vinilo updateVinilo(@PathVariable Integer id, @RequestBody Vinilo v) {
        v.setIdVin(id);
        return service.updateVinilo(v);
    }

    @DeleteMapping("/{id}")
    public void deleteVinilo(@PathVariable Integer id) {
        service.deleteVinilo(id);
    }

    @DeleteMapping("/all")
    public void deleteAllVinilos() {
        service.deleteAllVinilos();
    }
}
