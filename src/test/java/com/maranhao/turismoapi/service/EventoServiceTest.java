package com.maranhao.turismoapi.service;

import com.maranhao.turismoapi.dto.EventoRequest;
import com.maranhao.turismoapi.dto.EventoResponse;
import com.maranhao.turismoapi.exception.ResourceNotFoundException;
import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.model.Evento;
import com.maranhao.turismoapi.repository.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository repository;

    @InjectMocks
    private EventoService service;

    private Evento evento;
    private EventoRequest request;

    @BeforeEach
    void setUp() {
        evento = Evento.builder()
                .id(1L)
                .nome("Bumba Meu Boi")
                .descricao("Maior festa popular do Maranhão")
                .cidade("São Luís")
                .estado("Maranhão")
                .local("Lagoa da Jansen")
                .categoria(Categoria.CULTURAL)
                .dataInicio(LocalDate.of(2026, 6, 13))
                .dataFim(LocalDate.of(2026, 6, 30))
                .gratuito(true)
                .criadoEm(LocalDateTime.now())
                .build();

        request = new EventoRequest();
        request.setNome("Bumba Meu Boi");
        request.setDescricao("Maior festa popular do Maranhão");
        request.setCidade("São Luís");
        request.setLocal("Lagoa da Jansen");
        request.setCategoria(Categoria.CULTURAL);
        request.setDataInicio(LocalDate.of(2026, 6, 13));
        request.setDataFim(LocalDate.of(2026, 6, 30));
        request.setGratuito(true);
    }

    @Test
    @DisplayName("Deve listar todos os eventos")
    void deveListarTodosEventos() {
        when(repository.findAll()).thenReturn(List.of(evento));

        List<EventoResponse> resultado = service.listarTodos();

        assertThat(resultado).isNotEmpty();
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Bumba Meu Boi");
    }

    @Test
    @DisplayName("Deve buscar evento por ID com sucesso")
    void deveBuscarEventoPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(evento));

        EventoResponse resultado = service.buscarPorId(1L);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1L);
        assertThat(resultado.getNome()).isEqualTo("Bumba Meu Boi");
    }

    @Test
    @DisplayName("Deve lançar exceção quando evento não encontrado")
    void deveLancarExcecaoQuandoEventoNaoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Evento não encontrado");
    }

    @Test
    @DisplayName("Deve criar evento com sucesso")
    void deveCriarEvento() {
        when(repository.save(any(Evento.class))).thenReturn(evento);

        EventoResponse resultado = service.criar(request);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getNome()).isEqualTo("Bumba Meu Boi");
        verify(repository, times(1)).save(any(Evento.class));
    }

    @Test
    @DisplayName("Deve deletar evento com sucesso")
    void deveDeletarEvento() {
        when(repository.findById(1L)).thenReturn(Optional.of(evento));
        doNothing().when(repository).deleteById(1L);

        assertThatCode(() -> service.deletar(1L)).doesNotThrowAnyException();
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve listar eventos próximos")
    void deveListarEventosProximos() {
        when(repository.findByDataInicioGreaterThanEqualOrderByDataInicio(any(LocalDate.class)))
                .thenReturn(List.of(evento));

        List<EventoResponse> resultado = service.listarProximos();

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).getNome()).isEqualTo("Bumba Meu Boi");
    }
}
