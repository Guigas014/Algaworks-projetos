package com.algaworks.logisticaapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.logisticaapi.api.assembler.EntregaAssembler;
import com.algaworks.logisticaapi.api.model.DestinatarioModel;
import com.algaworks.logisticaapi.api.model.EntregaModel;
import com.algaworks.logisticaapi.api.model.input.EntregaInputModel;
import com.algaworks.logisticaapi.domain.model.Entrega;
import com.algaworks.logisticaapi.domain.repository.EntregaRepository;
import com.algaworks.logisticaapi.domain.service.FinalizacaoEntregaService;
import com.algaworks.logisticaapi.domain.service.SolicitacaoEntregaService;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
  
  private SolicitacaoEntregaService solicitacaoEntregaService;
  private EntregaRepository entregaRepository;
  private ModelMapper modelMapper;
  private EntregaAssembler entregaAssembler;
  private FinalizacaoEntregaService finalizacaoEntregaService;


  //Sem Representation Model
  //@PostMapping
  //@ResponseStatus(HttpStatus.CREATED)
  //public Entrega solicitar(@Valid  @RequestBody Entrega entrega) {
    //return solicitacaoEntregaService.solicitar(entrega);
  //}

  //Com Represantion Model
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaModel solicitar(@Valid  @RequestBody EntregaInputModel entregaInput) {
    Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);

    Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
    
    return entregaAssembler.toModel(entregaSolicitada);
  }

  //Sem Representaion Model
  //@GetMapping
  //public List<Entrega> listar() {
    //return entregaRepository.findAll();
  //}

  //Com Representaion Model
  @GetMapping
  public List<EntregaModel> listar() {
    return entregaAssembler.toCollectionModel(entregaRepository.findAll());
  }

  @GetMapping("/{entregaId}")
  public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
    return entregaRepository.findById(entregaId)
      .map(entrega -> {
        //EntregaModel entregaModel = modelMapper.map(entrega, EntregaModel.class);

        //EntregaModel entregaModel = new EntregaModel();
        //entregaModel.setId(entrega.getId());  
        //entregaModel.setNomeCliente(entrega.getCliente().getNome());  
        //entregaModel.setDestinatario(new DestinatarioModel());  
        //entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());  
        //entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());  
        //entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());  
        //entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());  
        //entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());  
        
        //entregaModel.setTaxa(entrega.getTaxa());  
        //entregaModel.setStatus(entrega.getStatus());  
        //entregaModel.setDataPedido(entrega.getDataPedido());  
        //entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());  

        //return ResponseEntity.ok(entregaModel);
        return ResponseEntity.ok(entregaAssembler.toModel(entrega));
      }).orElse(ResponseEntity.notFound().build());
  }


  @PutMapping("/{entregaId}/finalizacao")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void finalizar(@PathVariable Long entregaId) {
    finalizacaoEntregaService.finalizar(entregaId);
  }

}
