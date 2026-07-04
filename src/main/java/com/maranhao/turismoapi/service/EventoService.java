package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.EventoRequest;
import com.maranhao.turismoapi.dto.EventoResponse;
import com.maranhao.turismoapi.exception.ResourceNotFoundException;
import com.maranhao.turismoapi.model.Evento;
import com.maranhao.turismoapi.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository repository;

    public List<EventoResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(EventoResponse::fromEntity)
                .toList();
    }

    public EventoResponse buscarPorId(Long id) {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
        return EventoResponse.fromEntity(evento);
    }

    public List<EventoResponse> listarProximos() {
        return repository
                .findByDataInicioGreaterThanEqualOrderByDataInicio(LocalDate.now())
                .stream()
                .map(EventoResponse::fromEntity)
                .toList();
    }

    public EventoResponse criar(EventoRequest request) {
        Evento evento = Evento.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .cidade(request.getCidade())
                .estado("Maranhão")
                .local(request.getLocal())
                .imagemUrl(request.getImagemUrl())
                .dataInicio(request.getDataInicio())
                .dataFim(request.getDataFim())
                .categoria(request.getCategoria())
                .gratuito(request.getGratuito())
                .build();

        return EventoResponse.fromEntity(repository.save(evento));
    }

    public EventoResponse atualizar(Long id, EventoRequest request) {
        Evento evento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));

        evento.setNome(request.getNome());
        evento.setDescricao(request.getDescricao());
        evento.setCidade(request.getCidade());
        evento.setLocal(request.getLocal());
        evento.setImagemUrl(request.getImagemUrl());
        evento.setDataInicio(request.getDataInicio());
        evento.setDataFim(request.getDataFim());
        evento.setCategoria(request.getCategoria());
        evento.setGratuito(request.getGratuito());

        return EventoResponse.fromEntity(repository.save(evento));
    }
    public void deletar(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado"));
        repository.deleteById(id);
    }

}
