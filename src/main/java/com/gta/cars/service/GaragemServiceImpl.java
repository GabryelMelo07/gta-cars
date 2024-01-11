package com.gta.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.GaragemDTO;
import com.gta.cars.model.Garagem;
import com.gta.cars.repository.GaragemRepository;
import com.gta.cars.service.interfaces.GaragemService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GaragemServiceImpl implements GaragemService {

    @Autowired
    private GaragemRepository garagemRepository;
    
    @Override
    public List<Garagem> getAll() {
        return garagemRepository.findAll();
    }

    @Override
    public Garagem getById(long id) {
        return garagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Garagem inexistente."));
    }

    @Override
    public Garagem save(GaragemDTO dto) {
        Garagem garagem = new Garagem();
        garagem.setNome(dto.nome());
        garagem.setLocalizacao(dto.localizacao());
        garagem.setImagem(dto.imagem());
        return garagemRepository.save(garagem);
    }

    @Override
    public Garagem update(long id, GaragemDTO dto) {
        Garagem garagem = getById(id);
        garagem.setNome(dto.nome());
        garagem.setLocalizacao(dto.localizacao());
        garagem.setImagem(dto.imagem());
        return garagemRepository.save(garagem);
    }

    @Override
    public boolean delete(long id) {
        if (garagemRepository.existsById(id)) {
            garagemRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Garagem inexistente.");
        }
    }
    
}
