package com.gta.cars.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, DTO> {
    Page<T> getAll(Pageable pageable);

    T getById(long id);

    T save(DTO dto);

    T update(long id, DTO dto);

    boolean delete(long id);
}