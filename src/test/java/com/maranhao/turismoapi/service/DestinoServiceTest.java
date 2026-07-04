package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.DestinoRequest;
import com.maranhao.turismoapi.dto.DestinoResponse;
import com.maranhao.turismoapi.exception.ResourceNotFoundException;
import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.model.Destino;
import com.maranhao.turismoapi.repository.DestinoRepository;
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
class DestinoServiceTest {

    @Mock
    private DestinoRepository repository;

    @InjectMocks
    private DestinoService service;

    private Destino destino;
    private DestinoRequest request;

    @BeforeEach
    void setUp() {
        destino = Destino.builder()
                .id(1L)
                .nome("Lençóis Maranhenses")
                .descricao("Parque Nacional com dunas e lagoas")
                .cidade("Barreirinhas")
                .estado("Maranhão")
                .categoria(Categoria.NATUREZA)
                .avaliacaoMedia(0.0)
                .totalAvaliacoes(0)
                .criadoEm(LocalDateTime.now())
                .build();

        request = new DestinoRequest();
        request.setNome("Lençóis Maranhenses");
        request.setDescricao("Parque Nacional com dunas e lagoas");
        request.setCidade("Barreirinhas");
        request.setCategoria(Categoria.NATUREZA);
        request.setLatitude(-2.4833);
        request.setLongitude(-43.1167);
    }

    @Test
    @DisplayName("Deve listar todos os destinos")
    void deveListarTodosDestinos() {
        when(repository.findAll()).thenReturn(List.of(destino));

        List<DestinoResponse> resultado = service.listarTodos();

        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Lençóis Maranhenses");
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar destino por ID com sucesso")
    void deveBuscarDestinoPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(destino));

        DestinoResponse resultado = service.buscarPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("Lençóis Maranhenses");
    }

    @Test
    @DisplayName("Deve lançar exceção quando destino não encontrado")
    void deveLancarExcecaoQuandoDestinoNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Destino não encontrado");
    }

    @Test
    @DisplayName("Deve criar destino com sucesso")
    void deveCriarDestino() {
        when(repository.save(any(Destino.class))).thenReturn(destino);

        DestinoResponse resultado = service.criar(request);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Lençóis Maranhenses");
        verify(repository, times(1)).save(any(Destino.class));
    }

    @Test
    @DisplayName("Deve atualizar destino com sucesso")
    void deveAtualizarDestino() {
        when(repository.findById(1L)).thenReturn(Optional.of(destino));
        when(repository.save(any(Destino.class))).thenReturn(destino);

        DestinoResponse resultado = service.atualizar(1L, request);

        assertThat(resultado).isNotNull();
        verify(repository, times(1)).save(any(Destino.class));
    }

    @Test
    @DisplayName("Deve deletar destino com sucesso")
    void deveDeletarDestino() {
        when(repository.findById(1L)).thenReturn(Optional.of(destino));
        doNothing().when(repository).deleteById(1L);

        assertThatCode(() -> service.deletar(1L)).doesNotThrowAnyException();
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve buscar destinos por categoria")
    void deveBuscarPorCategoria() {
        when(repository.findByCategoria(Categoria.NATUREZA)).thenReturn(List.of(destino));

        List<DestinoResponse> resultado = service.buscarPorCategoria(Categoria.NATUREZA);

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).getCategoria()).isEqualTo(Categoria.NATUREZA);
    }
}
