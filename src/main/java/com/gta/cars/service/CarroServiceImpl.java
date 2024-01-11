package com.gta.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Override
    public List<Carro> getAll() {
        return carroRepository.findAll();
    }

    @Override
    public Carro getById(long id) {
        return carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Carro inexistente."));
    }

    @Override
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
    public boolean delete(long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Carro inexistente.");
        }
    }
    
}
