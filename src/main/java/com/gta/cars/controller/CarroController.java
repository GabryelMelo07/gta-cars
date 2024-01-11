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

import com.gta.cars.dto.CarroDTO;
import com.gta.cars.model.Carro;
import com.gta.cars.service.interfaces.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {
    
    @Autowired
    private CarroService carroService;
    
    @GetMapping
    public ResponseEntity<List<Carro>> getAll() {
        List<Carro> carros = carroService.getAll();
        return ResponseEntity.ok().body(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable long id) {
        Carro carro = carroService.getById(id);
        return ResponseEntity.ok().body(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> save(@RequestBody CarroDTO carroDto) {
        Carro carro = carroService.save(carroDto);
        return ResponseEntity.ok().body(carro);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> update(@PathVariable long id, @RequestBody CarroDTO carroDto) {
        Carro carro = carroService.update(id, carroDto);
        return ResponseEntity.ok().body(carro);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deletado = carroService.delete(id);

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }
    
}
