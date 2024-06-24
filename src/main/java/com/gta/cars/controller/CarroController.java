package com.gta.cars.controller;

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

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(carroService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Carro> save(@RequestBody CarroDTO carroDto) {
        return ResponseEntity.ok().body(carroService.save(carroDto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> update(@PathVariable long id, @RequestBody CarroDTO carroDto) {
        return ResponseEntity.ok().body(carroService.update(id, carroDto));
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
