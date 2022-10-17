package com.algaworks.logisticaapi.domain.service;

import com.algaworks.logisticaapi.domain.exception.NegocioException;
import com.algaworks.logisticaapi.domain.model.Cliente;
import com.algaworks.logisticaapi.domain.repository.ClienteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class CatalogoClienteService {
  
  private ClienteRepository clienteRepository;

  public Cliente buscar(Long clienteId) {
    return clienteRepository.findById(clienteId)
      .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
  }

  @Transactional
  public Cliente salvar(Cliente cliente) {
    boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
      .stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));

    if (emailEmUso) {
      throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
    }

    return clienteRepository.save(cliente);
  }

  @Transactional
  public void excluir(Long clienteId) {
    clienteRepository.deleteById(clienteId);
  }
}

