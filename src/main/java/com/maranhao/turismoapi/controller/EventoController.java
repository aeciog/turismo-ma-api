package com.maranhao.turismoapi.controller;

import com.maranhao.turismoapi.dto.EventoRequest;
import com.maranhao.turismoapi.dto.EventoResponse;
import com.maranhao.turismoapi.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
@Tag(name = "Eventos", description = "Eventos Culturais e Regionais do Maranhão")
public class EventoController {

    private final EventoService service;

    @GetMapping
    @Operation(summary = "Listar todos os evento")
    public ResponseEntity<List<EventoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento por ID")
    public ResponseEntity<EventoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/proximos")
    @Operation(summary = "Listar eventos dos próximos 30 dias")
    public ResponseEntity<List<EventoResponse>> listarProximos(){
        return ResponseEntity.ok(service.listarProximos());
    }

    @PostMapping
    @Operation(summary = "Criar novo evento")
    public ResponseEntity<EventoResponse>criar(@Valid @RequestBody EventoRequest evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(evento));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Remove evento")
    public ResponseEntity<EventoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody EventoRequest evento) {
        return ResponseEntity.ok(service.atualizar(id, evento));
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
