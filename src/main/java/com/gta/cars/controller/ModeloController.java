package com.gta.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.model.Modelo;
import com.gta.cars.service.interfaces.ModeloService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/modelo")
@SecurityRequirement(name = "Bearer Authentication")
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;
    
    @GetMapping
    public ResponseEntity<Page<Modelo>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(modeloService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(modeloService.getById(id));
    }

}
