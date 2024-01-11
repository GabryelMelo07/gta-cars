package com.gta.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.MarcaDTO;
import com.gta.cars.model.Marca;
import com.gta.cars.repository.MarcaRepository;
import com.gta.cars.service.interfaces.MarcaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaServiceImpl implements MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;
    
    @Override
    public List<Marca> getAll() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca getById(long id) {
        return marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca inexistente."));
    }

    @Override
    public Marca save(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setInspiracao(dto.inspiracao());
        return marcaRepository.save(marca);
    }

    @Override
    public Marca update(long id, MarcaDTO dto) {
        Marca marca = getById(id);
        marca.setNome(dto.nome());
        marca.setInspiracao(dto.inspiracao());
        return marcaRepository.save(marca);
    }

    @Override
    public boolean delete(long id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Marca inexistente.");
        }
    }
}
