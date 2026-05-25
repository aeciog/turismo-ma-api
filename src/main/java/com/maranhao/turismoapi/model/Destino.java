package com.maranhao.turismoapi.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private String cidade;

    private String estado = "Maranhão";

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    private Double latitude;
    private Double longitude;

    private Double avaliacaoMedia = 0.0;
    private Integer totalAvaliacoes = 0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public  void prePersiste(){
        this.criadoEm = LocalDateTime.now();
    }

}
