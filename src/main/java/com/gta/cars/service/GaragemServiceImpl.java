package com.gta.cars.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.GaragemDTO;
import com.gta.cars.model.Garagem;
import com.gta.cars.model.User;
import com.gta.cars.repository.GaragemRepository;
import com.gta.cars.repository.UserRepository;
import com.gta.cars.service.interfaces.GaragemService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GaragemServiceImpl implements GaragemService {

    @Autowired
    private GaragemRepository garagemRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Cacheable(value = "garagens", key = "'user_' + #userId + '_page_' + #pageable.pageNumber")
    @Override
    public Page<Garagem> getByUserId(UUID userId, Pageable pageable) {
        return garagemRepository.findByUserId(userId, pageable);
    }

    @Override
    public Garagem getById(long id) {
        return garagemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Garagem inexistente."));
    }

    @Override
    @CacheEvict(value = "garagens", allEntries = true)
    public Garagem save(GaragemDTO dto, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não existe."));
        Garagem garagem = new Garagem(null, dto.nome(), dto.localizacao(), dto.imagem(), user, new ArrayList<>());
        return garagemRepository.save(garagem);
    }

    @Override
    @CacheEvict(value = "garagens", allEntries = true)
    public Garagem update(long id, GaragemDTO dto) {
        Garagem garagem = getById(id);
        garagem.setNome(dto.nome());
        garagem.setLocalizacao(dto.localizacao());
        garagem.setImagem(dto.imagem());
        return garagemRepository.save(garagem);
    }

    @Override
    @CacheEvict(value = "garagens", allEntries = true)
    public boolean delete(long id) {
        if (garagemRepository.existsById(id)) {
            garagemRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Garagem inexistente.");
        }
    }
    
}
