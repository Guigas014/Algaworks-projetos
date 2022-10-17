package com.algaworks.logisticaapi.domain.service;

import com.algaworks.logisticaapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.logisticaapi.domain.model.Entrega;
import com.algaworks.logisticaapi.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BuscaEntregaService {

  private EntregaRepository entregaRepository;

  public Entrega buscar(Long entregaId) {
    return entregaRepository.findById(entregaId)
      .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
  }
  
}
