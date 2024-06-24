package com.gta.cars.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.ModeloDto;
import com.gta.cars.model.Marca;
import com.gta.cars.model.Modelo;
import com.gta.cars.model.Role;
import com.gta.cars.model.User;
import com.gta.cars.repository.ModeloRepository;
import com.gta.cars.repository.RoleRepository;
import com.gta.cars.repository.UserRepository;
import com.gta.cars.service.interfaces.MarcaService;
import com.gta.cars.service.interfaces.ModeloService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Cacheable(value = "modelos", key = "'page_' + #pageable.pageNumber")
    @Override
    public Page<Modelo> getAll(Pageable pageable) {
        return modeloRepository.findAll(pageable);
    }

    @Override
    public Modelo getById(long id) {
        return modeloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Modelo inexistente."));
    }

    @Override
    public Modelo save(ModeloDto dto, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Role role = roleRepository.findByNameIgnoreCase("admin");

        Marca marca = marcaService.getById(dto.marcaId());

        if (!user.getRoles().contains(role))
            throw new RuntimeException("Usuário não tem permissão de admin.");

        Modelo modelo = new Modelo();
        modelo.setNome(dto.nome());
        modelo.setInspiracao(dto.inspiracao());
        modelo.setClasse(dto.classe());
        modelo.setCapacidade(dto.capacidade());
        modelo.setImagens(dto.imagens());
        modelo.setMarca(marca);
        return modeloRepository.save(modelo);
    }

    @Override
    public Modelo update(long id, ModeloDto dto, UUID userId) {
        Modelo modelo = getById(id);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Role role = roleRepository.findByNameIgnoreCase("admin");

        if (!user.getRoles().contains(role))
            throw new RuntimeException("Usuário não tem permissão de admin.");

        Marca marca = marcaService.getById(dto.marcaId());
        modelo.setNome(dto.nome());
        modelo.setInspiracao(dto.inspiracao());
        modelo.setClasse(dto.classe());
        modelo.setCapacidade(dto.capacidade());
        modelo.setImagens(dto.imagens());
        modelo.setMarca(marca);
        return modeloRepository.save(modelo);        
    }

    @Override
    public boolean delete(long id, UUID userId) {
        Modelo modelo = getById(id);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Role role = roleRepository.findByNameIgnoreCase("admin");

        if (user.getRoles().contains(role)) {
            modeloRepository.delete(modelo);
            return true;
        } else {
            throw new RuntimeException("Não foi possível deletar o modelo: " + modelo.getNome());
        }
    }

}
