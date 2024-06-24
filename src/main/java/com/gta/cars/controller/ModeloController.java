package com.gta.cars.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.dto.ModeloDto;
import com.gta.cars.model.Modelo;
import com.gta.cars.service.interfaces.ModeloService;

@RestController
@RequestMapping("/modelo")
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

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Modelo> save(@RequestBody ModeloDto dto, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(modeloService.save(dto, UUID.fromString(token.getName())));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Modelo> update(@PathVariable long id, @RequestBody ModeloDto dto, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(modeloService.update(id, dto, UUID.fromString(token.getName())));
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable int id, JwtAuthenticationToken token) {
        boolean deletado = modeloService.delete(id, UUID.fromString(token.getName()));

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }

}
