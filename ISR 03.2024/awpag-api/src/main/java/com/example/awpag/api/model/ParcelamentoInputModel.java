package com.example.awpag.api.model;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

// Esse Representation Model é para a entrada de dados, ou seja, para os dados que vem do frontend.
// Obs.: os outros Representation Model que tem nesse programa, e não tem Input no nome, é de saída.
@Getter
@Setter
public class ParcelamentoInputModel {

      @NotBlank
      @Size(max = 20)
      private String descricao;

      @NotNull
      @Positive
      private BigDecimal valorTotal;

      @NotNull
      @Positive
      @Max(12)
      private Integer quantidadeParcelas;

      @Valid
      @NotNull
      private ClienteIdInput cliente;
}
