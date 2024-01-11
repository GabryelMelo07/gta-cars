package com.gta.cars.service.interfaces;

import java.util.List;

public interface BaseService<T, DTO> {
    List<T> getAll();

    T getById(long id);

    T save(DTO dto);

    T update(long id, DTO dto);

    boolean delete(long id);
}