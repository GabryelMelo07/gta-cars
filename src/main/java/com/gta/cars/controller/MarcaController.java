package com.gta.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.dto.MarcaDTO;
import com.gta.cars.model.Marca;
import com.gta.cars.service.interfaces.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    
    @Autowired
    private MarcaService marcaService;
    
    @GetMapping
    public ResponseEntity<List<Marca>> getAll() {
        List<Marca> marcas = marcaService.getAll();
        return ResponseEntity.ok().body(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getById(@PathVariable long id) {
        Marca marca = marcaService.getById(id);
        return ResponseEntity.ok().body(marca);
    }

    @PostMapping
    public ResponseEntity<Marca> save(@RequestBody MarcaDTO marcaDto) {
        Marca marca = marcaService.save(marcaDto);
        return ResponseEntity.ok().body(marca);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Marca> update(@PathVariable long id, @RequestBody MarcaDTO marcaDto) {
        Marca marca = marcaService.update(id, marcaDto);
        return ResponseEntity.ok().body(marca);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deletado = marcaService.delete(id);

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }
    
}
