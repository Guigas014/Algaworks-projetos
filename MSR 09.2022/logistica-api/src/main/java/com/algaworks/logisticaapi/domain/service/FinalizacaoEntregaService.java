package com.algaworks.logisticaapi.domain.service;

import com.algaworks.logisticaapi.domain.model.Entrega;
import com.algaworks.logisticaapi.domain.model.StatusEntrega;
import com.algaworks.logisticaapi.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

  private EntregaRepository entregaRepository;
  private BuscaEntregaService buscaEntregaService;

  
  @Transactional
  public void finalizar(Long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    entrega.finalizar();

    entregaRepository.save(entrega);
  } 

}

