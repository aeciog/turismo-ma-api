package com.maranhao.turismoapi.repository;

import com.maranhao.turismoapi.model.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {

    List<Rota> findByNomeContainingIgnoreCase(String nome);
}
