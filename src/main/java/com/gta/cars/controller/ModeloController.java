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

import com.gta.cars.dto.ModeloDTO;
import com.gta.cars.model.Modelo;
import com.gta.cars.service.interfaces.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;
    
    @GetMapping
    public ResponseEntity<List<Modelo>> getAll() {
        List<Modelo> modelos = modeloService.getAll();
        return ResponseEntity.ok().body(modelos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> getById(@PathVariable long id) {
        Modelo modelo = modeloService.getById(id);
        return ResponseEntity.ok().body(modelo);
    }

    @PostMapping
    public ResponseEntity<Modelo> save(@RequestBody ModeloDTO modeloDto) {
        Modelo modelo = modeloService.save(modeloDto);
        return ResponseEntity.ok().body(modelo);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Modelo> update(@PathVariable long id, @RequestBody ModeloDTO modeloDto) {
        Modelo modelo = modeloService.update(id, modeloDto);
        return ResponseEntity.ok().body(modelo);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deletado = modeloService.delete(id);

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }
    
}
