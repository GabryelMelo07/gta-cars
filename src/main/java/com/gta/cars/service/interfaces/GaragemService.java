package com.gta.cars.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gta.cars.dto.GaragemDto;
import com.gta.cars.model.Garagem;

public interface GaragemService {
    Page<Garagem> getByUserId(UUID userId, Pageable pageable);

    Garagem getById(long id);
    
    Garagem getById(long id, UUID userId);

    Garagem save(GaragemDto dto, UUID userId);

    Garagem update(long id, GaragemDto dto, UUID userId);

    boolean delete(long id, UUID userId);
}
