package com.gta.cars.service.interfaces;

import com.gta.cars.dto.CarroDTO;
import com.gta.cars.model.Carro;

public interface CarroService {
    Carro getById(long id);

    Carro save(CarroDTO dto);

    Carro update(long id, CarroDTO dto);

    boolean delete(long id);
}
