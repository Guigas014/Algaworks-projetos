package com.example.awpag.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// ISSO É UM DTO OU UM REPRESENTATION MODEL!!!!!!!!!!!!!!
public class ParcelamentoModel {

      private Long id;
      // private String nomeCliente;
      private ClienteResumoModel cliente;
      private String descricao;
      private BigDecimal valorTotal;
      private Integer parcelas;
      private OffsetDateTime dataCriacao;
}
