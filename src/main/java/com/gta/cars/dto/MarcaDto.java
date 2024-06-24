package com.gta.cars.dto;

import com.gta.cars.annotations.StringValida;

public record MarcaDto(@StringValida String nome, String inspiracao) {
}
