package com.gta.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.CarroDTO;
import com.gta.cars.model.Garagem;
import com.gta.cars.model.Modelo;
import com.gta.cars.model.Carro;
import com.gta.cars.repository.CarroRepository;
import com.gta.cars.service.interfaces.CarroService;
import com.gta.cars.service.interfaces.GaragemService;
import com.gta.cars.service.interfaces.ModeloService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarroServiceImpl implements CarroService {
    
    @Autowired
    private CarroRepository carroRepository;
    
    @Autowired
    private ModeloService modeloService;

    @Autowired
    private GaragemService garagemService;
    
    @Cacheable(value = "carros", key = "'pagina_' + #pageable.pageNumber")
    @Override
    public Page<Carro> getAll(Pageable pageable) {
        return carroRepository.findAll(pageable);
    }

    @Cacheable(value = "carro", key = "#id")
    @Override
    public Carro getById(long id) {
        return carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carro inexistente."));
    }

    @Override
    @CacheEvict(value = "garagens", allEntries = true)
    public Carro save(CarroDTO dto) {
        Modelo modelo = modeloService.getById(dto.modeloId());
        Garagem garagem = garagemService.getById(dto.garagemId());
        Carro carro = new Carro();
        carro.setImagem(dto.imagem());
        carro.setModelo(modelo);
        carro.setGaragem(garagem);
        return carroRepository.save(carro);
    }

    @Override
    @CacheEvict(value = "garagens", allEntries = true)
    public Carro update(long id, CarroDTO dto) {
        Modelo modelo = modeloService.getById(dto.modeloId());
        Garagem garagem = garagemService.getById(dto.garagemId());
        Carro carro = getById(id);
        carro.setImagem(dto.imagem());
        carro.setModelo(modelo);
        carro.setGaragem(garagem);
        return carroRepository.save(carro);
    }

    @Override
    @CacheEvict(value = "garagens", allEntries = true)
    public boolean delete(long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Carro inexistente.");
        }
    }
    
}
