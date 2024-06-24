package com.gta.cars.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gta.cars.dto.ModeloDto;
import com.gta.cars.model.Modelo;

public interface ModeloService {
    Page<Modelo> getAll(Pageable pageable);

    Modelo getById(long id);

    Modelo save(ModeloDto dto, UUID userId);

    Modelo update(long id, ModeloDto dto, UUID userId);
    
    boolean delete(long id, UUID userId);
}
