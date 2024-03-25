package com.gta.cars.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gta.cars.model.Modelo;

public interface ModeloService {
    Page<Modelo> getAll(Pageable pageable);

    Modelo getById(long id);
}
