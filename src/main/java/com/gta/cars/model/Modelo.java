package com.gta.cars.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gta.cars.model.enums.Classe;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "modelo")
@Data
public class Modelo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String nome;

    @Column(length = 100)
    private String inspiracao;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Classe classe;

    @Column
    private Integer capacidade;

    @OneToMany(mappedBy = "modelo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Imagem> imagens;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToMany(mappedBy = "modelo")
    @JsonIgnore
    private List<Carro> carros;
    
}
