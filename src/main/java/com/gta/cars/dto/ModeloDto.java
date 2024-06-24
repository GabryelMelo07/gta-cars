package com.gta.cars.dto;

import java.util.List;

import com.gta.cars.model.Imagem;
import com.gta.cars.model.enums.Classe;

public record ModeloDto(String nome, String inspiracao, Classe classe, Integer capacidade, List<Imagem> imagens, Long marcaId) {
}
