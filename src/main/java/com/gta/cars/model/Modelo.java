package com.gta.cars.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "marca")
@Data
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100)
    private String inspiracao;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Classe classe;

    @Column
    private int capacidade;

    @Column
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
}
