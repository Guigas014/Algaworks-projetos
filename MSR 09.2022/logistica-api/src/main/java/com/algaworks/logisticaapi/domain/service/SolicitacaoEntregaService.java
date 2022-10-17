package com.algaworks.logisticaapi.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import com.algaworks.logisticaapi.domain.exception.NegocioException;
import com.algaworks.logisticaapi.domain.model.Cliente;
import com.algaworks.logisticaapi.domain.model.Entrega;
import com.algaworks.logisticaapi.domain.model.StatusEntrega;
import com.algaworks.logisticaapi.domain.repository.EntregaRepository;
import com.algaworks.logisticaapi.domain.service.CatalogoClienteService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

  private EntregaRepository entregaRepository;
  private CatalogoClienteService catalogoClienteService;


  @Transactional
  public Entrega solicitar(Entrega entrega){
    
    Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());


    entrega.setCliente(cliente);
    entrega.setStatus(StatusEntrega.PENDENTE);
    entrega.setDataPedido(OffsetDateTime.now());
    
    return entregaRepository.save(entrega);  
  }

}
