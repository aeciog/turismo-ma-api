package com.maranhao.turismoapi.repository;

import com.maranhao.turismoapi.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByDataInicioGreaterThanEqualOrderByDataInicio (LocalDate data);
    List<Evento> findByCidadeContainingIgnoreCase(String cidade);
}
