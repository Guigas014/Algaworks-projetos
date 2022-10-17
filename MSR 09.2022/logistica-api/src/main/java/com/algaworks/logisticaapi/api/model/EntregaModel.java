package com.algaworks.logisticaapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.algaworks.logisticaapi.domain.model.StatusEntrega;
import com.algaworks.logisticaapi.api.model.DestinatarioModel;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EntregaModel {

  private Long id;
  //private String nomeCliente;
  private ClienteResumoModel cliente;
  private DestinatarioModel destinatario;
  private BigDecimal taxa;
  private StatusEntrega status;
  private OffsetDateTime dataPedido;
  private OffsetDateTime dataFinalizacao;
  
}

