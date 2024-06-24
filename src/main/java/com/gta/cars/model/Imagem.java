package com.gta.cars.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "imagem")
@Data
public class Imagem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "img_url")
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "carro_id")
    @JsonIgnore
    private Carro carro;
    
    @ManyToOne
    @JoinColumn(name = "modelo_id")
    @JsonIgnore
    private Modelo modelo;
    
}
