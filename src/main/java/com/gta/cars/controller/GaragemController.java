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

import com.gta.cars.dto.GaragemDTO;
import com.gta.cars.model.Garagem;
import com.gta.cars.service.interfaces.GaragemService;

@RestController
@RequestMapping("/garagem")
public class GaragemController {
    
    @Autowired
    private GaragemService garagemService;
    
    @GetMapping
    public ResponseEntity<List<Garagem>> getAll() {
        List<Garagem> garagems = garagemService.getAll();
        return ResponseEntity.ok().body(garagems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garagem> getById(@PathVariable long id) {
        Garagem garagem = garagemService.getById(id);
        return ResponseEntity.ok().body(garagem);
    }

    @PostMapping
    public ResponseEntity<Garagem> save(@RequestBody GaragemDTO garagemDto) {
        Garagem garagem = garagemService.save(garagemDto);
        return ResponseEntity.ok().body(garagem);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Garagem> update(@PathVariable long id, @RequestBody GaragemDTO garagemDto) {
        Garagem garagem = garagemService.update(id, garagemDto);
        return ResponseEntity.ok().body(garagem);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean deletado = garagemService.delete(id);

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }
    
}
