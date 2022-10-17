package com.algaworks.logisticaapi.domain.service;

import com.algaworks.logisticaapi.domain.model.Entrega;
import com.algaworks.logisticaapi.domain.model.Ocorrencia;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
  
  private BuscaEntregaService buscaEntregaService;

  //Altera a o objeto Entrega adicionando a Ocorrencia.
  @Transactional
  public Ocorrencia registrar(Long entregaId, String descricao) {
    Entrega entrega = buscaEntregaService.buscar(entregaId); 
    return entrega.adicionarOcorrencia(descricao);     
  }

}
