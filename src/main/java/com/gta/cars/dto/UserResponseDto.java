package com.gta.cars.dto;

import java.util.Set;
import java.util.UUID;

import com.gta.cars.model.Role;

public record UserResponseDto(UUID id, String username, String email, String nome, Set<Role> roles) {
}
