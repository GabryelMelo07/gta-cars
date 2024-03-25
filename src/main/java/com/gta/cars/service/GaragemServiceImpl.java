package com.gta.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    @Cacheable(value = "garagens", key = "'page_' + #pageable.pageNumber")
    @Override
    public Page<Garagem> getAll(Pageable pageable) {
        return garagemRepository.findAll(pageable);
    }

    @Cacheable(value = "garagem", key = "#id")
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
