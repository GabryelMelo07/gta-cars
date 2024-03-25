package com.gta.cars.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gta.cars.model.Carro;

public interface CarroRepository extends PagingAndSortingRepository<Carro, Long>, CrudRepository<Carro, Long> {
    
}
