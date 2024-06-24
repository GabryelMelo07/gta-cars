package com.gta.cars.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gta.cars.dto.MarcaDto;
import com.gta.cars.model.Marca;

public interface MarcaService {
    Page<Marca> getAll(Pageable pageable);

    Marca getById(long id);

    Marca save(MarcaDto dto, UUID userId);

    Marca update(long id, MarcaDto dto, UUID userId);
    
    boolean delete(long id, UUID userId);
}
