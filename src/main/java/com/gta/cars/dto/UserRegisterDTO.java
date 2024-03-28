package com.gta.cars.dto;

import com.gta.cars.annotations.SenhaValida;
import com.gta.cars.annotations.StringValida;

import jakarta.validation.constraints.Email;

public record UserRegisterDTO(
    @StringValida("Nome de usuário") String login,

    @StringValida("Senha") @SenhaValida String password,

    @StringValida("Nome") String nome,

    @StringValida("Email") @Email(message = "Email inválido") String email
) {
}
