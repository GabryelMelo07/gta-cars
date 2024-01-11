package com.gta.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gta.cars.model.Garagem;

public interface GaragemRepository extends JpaRepository<Garagem, Long> {
}
