package com.gta.cars.dto;

import java.time.LocalDateTime;

public record LoginResponseDto(String accessToken, LocalDateTime expiresIn) {
}
