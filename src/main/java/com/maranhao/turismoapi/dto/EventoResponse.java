package com.maranhao.turismoapi.dto;

import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.model.Evento;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private String cidade;
    private String estado;
    private String local;
    private String imagemUrl;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Categoria categoria;
    private Boolean gratuito;
    private LocalDateTime criadoEm;

    public static EventoResponse fromEntity(Evento evento) {
        EventoResponse response = new EventoResponse();
        response.setId(evento.getId());
        response.setNome(evento.getNome());
        response.setDescricao(evento.getDescricao());
        response.setCidade(evento.getCidade());
        response.setEstado(evento.getEstado());
        response.setLocal(evento.getLocal());
        response.setImagemUrl(evento.getImagemUrl());
        response.setDataInicio(evento.getDataInicio());
        response.setDataFim(evento.getDataFim());
        response.setCategoria(evento.getCategoria());
        response.setGratuito(evento.getGratuito());
        response.setCriadoEm(evento.getCriadoEm());
        return response;
    }

}
