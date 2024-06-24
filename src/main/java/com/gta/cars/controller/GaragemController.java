package com.gta.cars.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gta.cars.dto.GaragemDto;
import com.gta.cars.model.Garagem;
import com.gta.cars.service.interfaces.GaragemService;

@RestController
@RequestMapping("/garagem")
public class GaragemController {
    
    @Autowired
    private GaragemService garagemService;
    
    @GetMapping
    public ResponseEntity<Page<Garagem>> getGaragensByUser(Pageable pageable, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(garagemService.getByUserId(UUID.fromString(token.getName()), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garagem> getById(@PathVariable long id, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(garagemService.getById(id, UUID.fromString(token.getName())));
    }

    @PostMapping
    public ResponseEntity<Garagem> save(@RequestBody GaragemDto garagemDto, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(garagemService.save(garagemDto, UUID.fromString(token.getName())));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Garagem> update(@PathVariable long id, @RequestBody GaragemDto garagemDto, JwtAuthenticationToken token) {
        return ResponseEntity.ok().body(garagemService.update(id, garagemDto, UUID.fromString(token.getName())));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id, JwtAuthenticationToken token) {
        boolean deletado = garagemService.delete(id, UUID.fromString(token.getName()));

        if (deletado)
            return new ResponseEntity<Boolean>(deletado, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(deletado, HttpStatus.NOT_FOUND);
    }
    
}
