package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.RotaRequest;
import com.maranhao.turismoapi.dto.RotaResponse;
import com.maranhao.turismoapi.exception.ResourceNotFoundException;
import com.maranhao.turismoapi.model.Destino;
import com.maranhao.turismoapi.model.Rota;
import com.maranhao.turismoapi.repository.DestinoRepository;
import com.maranhao.turismoapi.repository.RotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaService {

    private final RotaRepository rotaRepository;
    private final DestinoRepository destinoRepository;

    public List<RotaResponse> listarTodos() {
        return rotaRepository.findAll()
                .stream()
                .map(RotaResponse::fromEntity)
                .toList();
    }

    public RotaResponse buscarPorId(Long id) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada"));
        return RotaResponse.fromEntity(rota);
    }

    public RotaResponse criar(RotaRequest request) {
        List<Destino> destinos = destinoRepository.findAllById(
                request.getDestinoIds() != null ? request.getDestinoIds() : List.of()
        );

        Rota rota = Rota.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .imagemUrl(request.getImagemUrl())
                .duracaoDias(request.getDuracaoDias())
                .distanciaKm(request.getDistanciaKm())
                .dificuldade(request.getDificuldade())
                .destinos(destinos)
                .build();

        return RotaResponse.fromEntity(rotaRepository.save(rota));

    }

    public RotaResponse atualizar(Long id, RotaRequest request) {
        Rota rota = rotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada"));

        List<Destino> destinos = destinoRepository.findAllById(
                request.getDestinoIds() != null ? request.getDestinoIds() : List.of()
        );
        rota.setNome(request.getNome());
        rota.setDescricao(request.getDescricao());
        rota.setImagemUrl(request.getImagemUrl());
        rota.setDuracaoDias(request.getDuracaoDias());
        rota.setDistanciaKm(request.getDistanciaKm());
        rota.setDificuldade(request.getDificuldade());
        rota.setDestinos(destinos);


        return RotaResponse.fromEntity(rotaRepository.save(rota));

    }


    public void deletar (Long id){
        rotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rota inexistente"));
        rotaRepository.deleteById(id);
    }
}
