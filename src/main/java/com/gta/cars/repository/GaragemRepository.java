package com.gta.cars.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gta.cars.model.Garagem;

public interface GaragemRepository extends PagingAndSortingRepository<Garagem, Long>, CrudRepository<Garagem, Long> {

    @Query("SELECT g FROM Garagem g WHERE g.user.id = :userId")
    Page<Garagem> findByUserId(UUID userId, Pageable pageable);
    
}
