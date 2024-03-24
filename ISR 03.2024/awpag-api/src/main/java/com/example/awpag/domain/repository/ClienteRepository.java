package com.example.awpag.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.awpag.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

      List<Cliente> findByNome(String nome);

      List<Cliente> findByNomeContaining(String nome);

      Optional<Cliente> findByEmail(String email);

      // Uma interface não é instanciável, porém o Spring faz essa mágica.
      // Só assim é possível instanciar essa interface no service.
}
