package com.algaworks.logisticaapi.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.algaworks.logisticaapi.domain.model.Cliente;
import com.algaworks.logisticaapi.domain.repository.ClienteRepository;
import com.algaworks.logisticaapi.domain.service.CatalogoClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clientes")
public class ClienteController {


  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private CatalogoClienteService catalogoClienteService;


  //Lista todos
  @GetMapping
  public List<Cliente> listar() {
    return clienteRepository.findAll();
  }

  //Lista um pelo ID
  @GetMapping("/{clienteId}")
  public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
    return clienteRepository.findById(clienteId)
      .map(cliente -> ResponseEntity.ok(cliente))
      .orElse(ResponseEntity.notFound().build());

    //if (cliente.isPresent()) {
      //return ResponseEntity.ok(cliente.get());
    //}

    //return ResponseEntity.notFound().build();
  }

  //Adiciona um cliente
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
    //return clienteRepository.save(cliente); 
    return catalogoClienteService.salvar(cliente); 
  }

  //Atualiza um cliente
  @PutMapping("/{clienteId}")
  public ResponseEntity<Cliente> atualizar(
      @PathVariable Long clienteId, 
      @Valid @RequestBody Cliente cliente) {
      
      if (!clienteRepository.existsById(clienteId)) {
        return ResponseEntity.notFound().build();
      } 

      cliente.setId(clienteId);
      //cliente = clienteRepository.save(cliente);
      cliente = catalogoClienteService.salvar(cliente);

      return ResponseEntity.ok(cliente);
  } 

  //Deleta um cliente
  @DeleteMapping("/{clienteId}")
  public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
      if (!clienteRepository.existsById(clienteId)) {
        return ResponseEntity.notFound().build();
      } 
      
      //clienteRepository.deleteById(clienteId);
      catalogoClienteService.excluir(clienteId);

      return ResponseEntity.noContent().build();
  }

}


