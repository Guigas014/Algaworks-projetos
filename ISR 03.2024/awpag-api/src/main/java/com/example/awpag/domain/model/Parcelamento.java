package com.example.awpag.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.example.awpag.domain.validation.ValidationGroups;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Parcelamento {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @EqualsAndHashCode.Include
      private Long id;

      // Essas duas primeiras validações são usadas na validação em cascata para
      // validar o ClienteId.
      @Valid
      @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
      @NotNull
      @ManyToOne
      // @JoinColumn(name = "cliente_id") não é necessário, mas pode ser usado.
      private Cliente cliente;

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

      // private LocalDateTime dataCriacao;
      private OffsetDateTime dataCriacao;
}
