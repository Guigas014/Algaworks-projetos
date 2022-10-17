package com.algaworks.logisticaapi.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.algaworks.logisticaapi.api.assembler.OcorrenciaAssembler;
import com.algaworks.logisticaapi.api.model.OcorrenciaModel;
import com.algaworks.logisticaapi.api.model.input.OcorrenciaInput;
import com.algaworks.logisticaapi.domain.model.Entrega;
import com.algaworks.logisticaapi.domain.model.Ocorrencia;
import com.algaworks.logisticaapi.domain.service.BuscaEntregaService;
import com.algaworks.logisticaapi.domain.service.RegistroOcorrenciaService;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RequestMapping("/entregas/{entregaId}/ocorrencias")
@RestController
public class OcorrenciaController {
  
  private BuscaEntregaService buscaEntregaService;
  private RegistroOcorrenciaService registroOcorrenciaService;
  private OcorrenciaAssembler ocorrenciaAssembler;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OcorrenciaModel registrar(@PathVariable Long entregaId,
      @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
     
    Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
      .registrar(entregaId, ocorrenciaInput.getDescricao());


    return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
  }


  @GetMapping
  public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
  }


  //Arrumar esse método.
  @GetMapping("/{ocorrenciaId}")
  public OcorrenciaModel buscar(
      @PathVariable Long entregaId, 
      @PathVariable Long ocorrenciaId) {
    Entrega entrega = buscaEntregaService.buscar(entregaId);

    Ocorrencia ocorrenciaBuscada = new Ocorrencia();

    for (Ocorrencia ocorrencia : entrega.getOcorrencias()) {
      if (ocorrencia.getId() == ocorrenciaId) {
        ocorrenciaBuscada = ocorrencia;
      } else {
        System.out.println("Ocorrência não encontrada");
      }
    }

    return ocorrenciaAssembler.toModel(ocorrenciaBuscada);
  }

}

