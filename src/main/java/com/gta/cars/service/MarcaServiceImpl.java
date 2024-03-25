package com.gta.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gta.cars.model.Marca;
import com.gta.cars.repository.MarcaRepository;
import com.gta.cars.service.interfaces.MarcaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaServiceImpl implements MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    @Cacheable(value = "marcas", key = "'page_' + #pageable.pageNumber")
    @Override
    public Page<Marca> getAll(Pageable pageable) {
        return marcaRepository.findAll(pageable);
    }

    @Cacheable(value = "marca", key = "#id")
    @Override
    public Marca getById(long id) {
        return marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca inexistente."));
    }

}
