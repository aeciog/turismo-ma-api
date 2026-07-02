package com.maranhao.turismoapi.dto;


import com.maranhao.turismoapi.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private  String descricao;

    @NotBlank(message = "Cidade é obrigatório")
    private String  cidade;

    private String local;

    private String imagemUrl;

    @NotNull(message = "Data de inicio é obrigatório")
    private LocalDate dataInicio;

    private LocalDate dataFim;

    private Categoria categoria;

    private Boolean gratuito = true;

}
