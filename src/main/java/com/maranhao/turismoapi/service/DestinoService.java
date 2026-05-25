package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.DestinoRequest;
import com.maranhao.turismoapi.dto.DestinoResponse;
import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.model.Destino;
import com.maranhao.turismoapi.repository.DestinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinoService {

    private final DestinoRepository repository;

    public List<DestinoResponse> listarTodos(){
        return repository.findAll()
                .stream()
                .map(DestinoResponse::fromEntity)
                .toList();
    }

    public DestinoResponse buscarPorId(Long id){
        Destino destino = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        return DestinoResponse.fromEntity(destino);
    }

    public List<DestinoResponse> buscarPorCategoria(Categoria categoria){
        return repository.findByCategoria(categoria)
                .stream()
                .map(DestinoResponse::fromEntity)
                .toList();
    }

    public DestinoResponse criar(DestinoRequest request) {
        Destino destino = Destino.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .cidade(request.getCidade())
                .estado("Maranhão")           // <- adicione
                .imagemUrl(request.getImagemUrl())
                .categoria(request.getCategoria())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .avaliacaoMedia(0.0)          // <- adicione
                .totalAvaliacoes(0)           // <- adicione
                .build();

        return DestinoResponse.fromEntity(repository.save(destino));
    }

public DestinoResponse atualizar(Long id, DestinoRequest request) {
     Destino destino = repository.findById(id)
             .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

     destino.setNome(request.getNome());
     destino.setDescricao(request.getDescricao());
     destino.setCidade(request.getCidade());
     destino.setImagemUrl(request.getImagemUrl());
     destino.setCategoria(request.getCategoria());
     destino.setLatitude(request.getLatitude());
     destino.setLongitude(request.getLongitude());
     return DestinoResponse.fromEntity(repository.save(destino));
}

public void deletar(Long id){
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        repository.deleteById(id);
}


}
