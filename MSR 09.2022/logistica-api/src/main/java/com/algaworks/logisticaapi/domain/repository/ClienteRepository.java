package com.algaworks.logisticaapi.domain.repository;

import java.util.List;
import java.util.Optional;

import com.algaworks.logisticaapi.domain.model.Cliente;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepositoryImplementation<Cliente, Long> {

  Optional<Cliente> findByEmail(String email);

}

