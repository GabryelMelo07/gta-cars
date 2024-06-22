package com.gta.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.model.Marca;
import com.gta.cars.service.interfaces.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    
    @Autowired
    private MarcaService marcaService;
    
    @GetMapping
    public ResponseEntity<Page<Marca>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(marcaService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(marcaService.getById(id));
    }
    
}
