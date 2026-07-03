package com.maranhao.turismoapi.dto;

import com.maranhao.turismoapi.model.Rota;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RotaResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String imagemUrl;
    private Integer duracaoDias;
    private Double distanciaKm;
    private String dificuldade;
    private List<DestinoResponse> destinos;
    private LocalDateTime criadoEm;

    public static RotaResponse fromEntity(Rota rota) {
        RotaResponse response = new RotaResponse();
        response.setId(rota.getId());
        response.setNome(rota.getNome());
        response.setDescricao(rota.getDescricao());
        response.setImagemUrl(rota.getImagemUrl());
        response.setDuracaoDias(rota.getDuracaoDias());
        response.setDistanciaKm(rota.getDistanciaKm());
        response.setDificuldade(rota.getDificuldade());
        response.setCriadoEm(rota.getCriadoEm());
        if (rota.getDestinos() != null) {
            response.setDestinos(
                    rota.getDestinos().stream()
                            .map(DestinoResponse::fromEntity)
                            .toList()
            );
        }
        return response;
    }
}
