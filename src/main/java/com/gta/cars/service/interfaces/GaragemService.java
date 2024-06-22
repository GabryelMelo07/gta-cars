package com.gta.cars.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gta.cars.dto.GaragemDTO;
import com.gta.cars.model.Garagem;

public interface GaragemService {
    Page<Garagem> getByUserId(UUID userId, Pageable pageable);

    Garagem getById(long id);

    Garagem save(GaragemDTO dto, UUID userId);

    Garagem update(long id, GaragemDTO dto);

    boolean delete(long id);
}
