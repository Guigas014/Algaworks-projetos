package com.algaworks.logisticaapi.domain.repository;

import com.algaworks.logisticaapi.domain.model.Entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
  
}

