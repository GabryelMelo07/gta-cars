package com.gta.cars.dto;

import com.gta.cars.annotations.StringValida;

public record UserLoginDTO(@StringValida String login, @StringValida String password) {
}