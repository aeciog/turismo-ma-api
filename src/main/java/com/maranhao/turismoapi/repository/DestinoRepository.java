package com.maranhao.turismoapi.repository;

import com.maranhao.turismoapi.model.Categoria;
import com.maranhao.turismoapi.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {

    List<Destino> findByCategoria(Categoria categoria);
    List<Destino> findByCidadeContainingIgnoreCase(String cidade);
    List<Destino> findByNomeContainingIgnoreCase(String nome);
}

