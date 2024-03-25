package com.gta.cars.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gta.cars.model.Garagem;

public interface GaragemRepository extends PagingAndSortingRepository<Garagem, Long>, CrudRepository<Garagem, Long> {
}
