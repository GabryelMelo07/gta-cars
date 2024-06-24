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

import com.gta.cars.dto.MarcaDto;
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

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Marca> save(@RequestBody MarcaDto dto, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(marcaService.save(dto, UUID.fromString(token.getName())));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Marca> update(@PathVariable long id, @RequestBody MarcaDto dto, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(marcaService.update(id, dto, UUID.fromString(token.getName())));
    }

    @DeleteMapping("/deletar/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable int id, JwtAuthenticationToken token) {
        boolean deletado = marcaService.delete(id, UUID.fromString(token.getName()));

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }
    
}
