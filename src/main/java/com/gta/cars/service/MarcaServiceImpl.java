package com.gta.cars.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gta.cars.dto.MarcaDto;
import com.gta.cars.model.Marca;
import com.gta.cars.model.Role;
import com.gta.cars.model.User;
import com.gta.cars.repository.MarcaRepository;
import com.gta.cars.repository.RoleRepository;
import com.gta.cars.repository.UserRepository;
import com.gta.cars.service.interfaces.MarcaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MarcaServiceImpl implements MarcaService {
    
    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Cacheable(value = "marcas", key = "'page_' + #pageable.pageNumber")
    @Override
    public Page<Marca> getAll(Pageable pageable) {
        return marcaRepository.findAll(pageable);
    }

    @Override
    public Marca getById(long id) {
        return marcaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca inexistente."));
    }

    @Override
    public Marca save(MarcaDto dto, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Role role = roleRepository.findByNameIgnoreCase("admin");

        if (!user.getRoles().contains(role))
            throw new RuntimeException("Usuário não tem permissão de admin.");

        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setInspiracao(dto.inspiracao());
        return marcaRepository.save(marca);
    }

    @Override
    public Marca update(long id, MarcaDto dto, UUID userId) {
        Marca marca = marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Marca não encontrada."));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Role role = roleRepository.findByNameIgnoreCase("admin");

        if (!user.getRoles().contains(role))
            throw new RuntimeException("Usuário não tem permissão de admin.");

        marca.setNome(dto.nome());
        marca.setInspiracao(dto.inspiracao());
        return marcaRepository.save(marca);
    }

    @Override
    public boolean delete(long id, UUID userId) {
        Marca marca = getById(id);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        Role role = roleRepository.findByNameIgnoreCase("admin");

        if (user.getRoles().contains(role)) {
            marcaRepository.delete(marca);
            return true;
        } else {
            throw new RuntimeException("Não foi possível deletar a marca: " + marca.getNome());
        }
    }

}
