package com.maranhao.turismoapi.controller;

import com.maranhao.turismoapi.dto.DestinoRequest;
import com.maranhao.turismoapi.dto.DestinoResponse;
import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.service.DestinoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Destinos", description = "Pontos turísticos do Maranhão")
public class DestinoController {

    private final DestinoService service;

    @GetMapping
    @Operation(summary = "Lista todos os destinos")
    public ResponseEntity<List<DestinoResponse>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar destino por ID")
    public ResponseEntity<DestinoResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Buscar destinos por categoria")
    public ResponseEntity<List<DestinoResponse>> buscarPorCategoria(@PathVariable Categoria categoria){
        return ResponseEntity.ok(service.buscarPorCategoria(categoria));
    }

    @PostMapping("/{id}")
    @Operation(summary = "Criar novo destino")
    public ResponseEntity<DestinoResponse> criar(@Valid @RequestBody DestinoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinoResponse> atualizar(@PathVariable Long id,
                                                     @Valid @RequestBody DestinoRequest request){
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar destino")
    public ResponseEntity<Void> deletar (@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
