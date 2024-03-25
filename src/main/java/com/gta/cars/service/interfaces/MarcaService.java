package com.gta.cars.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gta.cars.model.Marca;

public interface MarcaService {
    Page<Marca> getAll(Pageable pageable);

    Marca getById(long id);
}
