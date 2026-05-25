package com.maranhao.turismoapi.dto;

import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.model.Destino;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DestinoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String cidade;
    private String estado;
    private String imagemUrl;
    private Categoria categoria;
    private Double latitude;
    private Double longitude;
    private Double avaliacaoMedia;
    private Integer totalAvaliacoes;
    private LocalDateTime criadoEm;

    public static DestinoResponse fromEntity(Destino destino) {
        DestinoResponse response = new DestinoResponse();
        response.setId(destino.getId());
        response.setNome(destino.getNome());
        response.setDescricao(destino.getDescricao());
        response.setCidade(destino.getCidade());
        response.setEstado(destino.getEstado());
        response.setImagemUrl(destino.getImagemUrl());
        response.setCategoria(destino.getCategoria());
        response.setLatitude(destino.getLatitude());
        response.setLongitude(destino.getLongitude());
        response.setAvaliacaoMedia(destino.getAvaliacaoMedia());
        response.setTotalAvaliacoes(destino.getTotalAvaliacoes());
        response.setCriadoEm(destino.getCriadoEm());
        return response;
    }
}