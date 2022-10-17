package com.algaworks.logisticaapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.logisticaapi.domain.ValidationGroups;
import com.algaworks.logisticaapi.domain.exception.NegocioException;
import com.algaworks.logisticaapi.domain.model.Cliente;
import com.algaworks.logisticaapi.domain.model.Destinatario;
import com.algaworks.logisticaapi.domain.model.StatusEntrega;
import com.algaworks.logisticaapi.domain.model.Ocorrencia;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Valid
  @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
  @NotNull
  @ManyToOne
  private Cliente cliente;

  @Valid
  @NotNull
  @Embedded
  private Destinatario destinatario;

  @NotNull
  private BigDecimal taxa;
  
  @Enumerated(EnumType.STRING)
  @JsonProperty(access = Access.READ_ONLY)
  private StatusEntrega status;

  @JsonProperty(access = Access.READ_ONLY)
  private OffsetDateTime dataPedido;

  @JsonProperty(access = Access.READ_ONLY)
  private OffsetDateTime dataFinalizacao;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "entrega", cascade = CascadeType.ALL)
  private List<Ocorrencia> ocorrencias = new ArrayList<>();
  

  public Ocorrencia adicionarOcorrencia(String descricao) {
    Ocorrencia ocorrencia = new Ocorrencia();

    ocorrencia.setDescricao(descricao);
    ocorrencia.setDataRegistro(OffsetDateTime.now());
    ocorrencia.setEntrega(this);

    this.getOcorrencias().add(ocorrencia);

    return ocorrencia;
  }


  public void finalizar() {
    if (!podeSerFinalizada()) {
      throw new NegocioException("Entrega não pode ser finalizada");
    }

    setStatus(StatusEntrega.FINALIZADA);
    setDataFinalizacao(OffsetDateTime.now());
  }

  public boolean podeSerFinalizada() {
    return StatusEntrega.PENDENTE.equals(getStatus());
  }

}

