package com.gta.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gta.cars.model.Imagem;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {
    
}
