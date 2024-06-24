package com.gta.cars.dto;

import java.time.LocalDateTime;

import com.gta.cars.model.User;

public record LoginResponseDto(String accessToken, LocalDateTime expiresIn, User user) {
}
