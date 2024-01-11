package com.gta.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gta.cars.model.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}
