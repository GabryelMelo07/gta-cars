package com.gta.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gta.cars.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
