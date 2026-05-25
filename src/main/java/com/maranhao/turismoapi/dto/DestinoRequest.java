package com.maranhao.turismoapi.dto;

import com.maranhao.turismoapi.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DestinoRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String descricao;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    private String imagemUrl;

    @NotNull(message = "Categoria é obrigatória")
    private Categoria categoria;

    private Double latitude;
    private Double longitude;
}