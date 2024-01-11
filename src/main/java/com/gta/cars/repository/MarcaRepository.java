package com.gta.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gta.cars.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
