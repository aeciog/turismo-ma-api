package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.RotaRequest;
import com.maranhao.turismoapi.dto.RotaResponse;
import com.maranhao.turismoapi.exception.ResourceNotFoundException;
import com.maranhao.turismoapi.model.Destino;
import com.maranhao.turismoapi.model.Rota;
import com.maranhao.turismoapi.repository.DestinoRepository;
import com.maranhao.turismoapi.repository.RotaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RotaServiceTest {

    @Mock
    private RotaRepository rotaRepository;

    @Mock
    private DestinoRepository destinoRepository;

    @InjectMocks
    private RotaService service;

    private Rota rota;
    private RotaRequest request;

    @BeforeEach
    void setUp() {
        rota = Rota.builder()
                .id(1L)
                .nome("Rota das Praias")
                .descricao("Circuito pelas praias de São Luís")
                .duracaoDias(2)
                .distanciaKm(45.5)
                .dificuldade("Fácil")
                .destinos(List.of())
                .criadoEm(LocalDateTime.now())
                .build();

        request = new RotaRequest();
        request.setNome("Rota das Praias");
        request.setDescricao("Circuito pelas praias de São Luís");
        request.setDuracaoDias(2);
        request.setDistanciaKm(45.5);
        request.setDificuldade("Fácil");
        request.setDestinoIds(List.of());
    }

    @Test
    @DisplayName("Deve listar todas as rotas")
    void deveListarTodasRotas() {
        when(rotaRepository.findAll()).thenReturn(List.of(rota));

        List<RotaResponse> resultado = service.listarTodos();

        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Rota das Praias");
    }

    @Test
    @DisplayName("Deve buscar rota por ID com sucesso")
    void deveBuscarRotaPorId() {
        when(rotaRepository.findById(1L)).thenReturn(Optional.of(rota));

        RotaResponse resultado = service.buscarPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("Rota das Praias");
    }

    @Test
    @DisplayName("Deve lançar exceção quando rota não encontrada")
    void deveLancarExcecaoQuandoRotaNaoEncontrada() {
        when(rotaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Rota não encontrada");
    }

    @Test
    @DisplayName("Deve criar rota com sucesso")
    void deveCriarRota() {
        when(destinoRepository.findAllById(any())).thenReturn(List.of());
        when(rotaRepository.save(any(Rota.class))).thenReturn(rota);

        RotaResponse resultado = service.criar(request);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Rota das Praias");
        verify(rotaRepository, times(1)).save(any(Rota.class));
    }

    @Test
    @DisplayName("Deve deletar rota com sucesso")
    void deveDeletarRota() {
        when(rotaRepository.findById(1L)).thenReturn(Optional.of(rota));
        doNothing().when(rotaRepository).deleteById(1L);

        assertThatCode(() -> service.deletar(1L)).doesNotThrowAnyException();
        verify(rotaRepository, times(1)).deleteById(1L);
    }
}
