package com.gta.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.ModeloDTO;
import com.gta.cars.model.Classe;
import com.gta.cars.model.Marca;
import com.gta.cars.model.Modelo;
import com.gta.cars.repository.ModeloRepository;
import com.gta.cars.service.interfaces.MarcaService;
import com.gta.cars.service.interfaces.ModeloService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaService marcaService;
    
    @Override
    public List<Modelo> getAll() {
        return modeloRepository.findAll();
    }

    @Override
    public Modelo getById(long id) {
        return modeloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Modelo inexistente."));
    }

    @Override
    public Modelo save(ModeloDTO dto) {
        Marca marca = marcaService.getById(dto.marcaId());
        Modelo modelo = new Modelo();
        modelo.setNome(dto.nome());
        modelo.setInspiracao(dto.inspiracao());
        modelo.setClasse(Classe.valueOf(dto.classe().toUpperCase()));
        modelo.setCapacidade(dto.capacidade());
        modelo.setImagem(dto.imagem());
        modelo.setMarca(marca);
        return modeloRepository.save(modelo);
    }

    @Override
    public Modelo update(long id, ModeloDTO dto) {
        Marca marca = marcaService.getById(dto.marcaId());
        Modelo modelo = getById(id);
        modelo.setNome(dto.nome());
        modelo.setInspiracao(dto.inspiracao());
        modelo.setClasse(Classe.valueOf(dto.classe().toUpperCase()));
        modelo.setCapacidade(dto.capacidade());
        modelo.setImagem(dto.imagem());
        modelo.setMarca(marca);
        return modeloRepository.save(modelo);
    }

    @Override
    public boolean delete(long id) {
        if (modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Modelo inexistente.");
        }
    }
}
