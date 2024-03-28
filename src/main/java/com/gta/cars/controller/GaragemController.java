package com.gta.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/garagem")
@SecurityRequirement(name = "Bearer Authentication")
public class GaragemController {
    
    @Autowired
    private GaragemService garagemService;
    
    @GetMapping
    public ResponseEntity<Page<Garagem>> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(garagemService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garagem> getById(@PathVariable long id) {
        return ResponseEntity.ok().body(garagemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Garagem> save(@RequestBody GaragemDTO garagemDto) {
        return ResponseEntity.ok().body(garagemService.save(garagemDto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Garagem> update(@PathVariable long id, @RequestBody GaragemDTO garagemDto) {
        return ResponseEntity.ok().body(garagemService.update(id, garagemDto));
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
