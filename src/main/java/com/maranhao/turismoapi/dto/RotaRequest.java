package com.maranhao.turismoapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RotaRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;

    private String imagemUrl;

    private Integer duracaoDias;

    private Double distanciaKm;

    private String dificuldade;

    private List<Long> destinoIds;
}
