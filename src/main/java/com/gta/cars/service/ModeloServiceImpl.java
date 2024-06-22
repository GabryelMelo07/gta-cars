package com.gta.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gta.cars.model.Modelo;
import com.gta.cars.repository.ModeloRepository;
import com.gta.cars.service.interfaces.ModeloService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Cacheable(value = "modelos", key = "'page_' + #pageable.pageNumber")
    @Override
    public Page<Modelo> getAll(Pageable pageable) {
        return modeloRepository.findAll(pageable);
    }

    @Override
    public Modelo getById(long id) {
        return modeloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Modelo inexistente."));
    }

}
