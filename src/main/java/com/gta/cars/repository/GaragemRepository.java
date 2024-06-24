package com.gta.cars.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gta.cars.model.Garagem;

public interface GaragemRepository extends PagingAndSortingRepository<Garagem, Long>, CrudRepository<Garagem, Long> {

    @Query("SELECT g FROM Garagem g JOIN FETCH g.carros c WHERE g.user.id = :userId ORDER BY c.id")
    Page<Garagem> findByUserId(UUID userId, Pageable pageable);
    
}
