package com.maranhao.turismoapi.controller;

import com.maranhao.turismoapi.dto.RotaRequest;
import com.maranhao.turismoapi.dto.RotaResponse;
import com.maranhao.turismoapi.model.Rota;
import com.maranhao.turismoapi.service.RotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rotas")
@RequiredArgsConstructor
@Tag(name = "Rotas", description = "Rotas turisticas do Maranhão")
public class RotaController {

    private final RotaService service;

    @GetMapping
    @Operation(summary = "Lista todas as rotas")
    public ResponseEntity<List<RotaResponse>> ListarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca rota por ID")
    public ResponseEntity<RotaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Criar nova rota")
    public ResponseEntity<RotaResponse> criar(@Valid @RequestBody RotaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar rota")
    public ResponseEntity<RotaResponse> atualizar(@PathVariable Long id,
                                                  @Valid @RequestBody RotaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove rota")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();

    }
}
