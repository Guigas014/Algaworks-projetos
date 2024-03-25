package com.example.awpag.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.awpag.api.assembler.ParcelamentoAssembler;
import com.example.awpag.api.model.ParcelamentoInputModel;
import com.example.awpag.api.model.ParcelamentoModel;
import com.example.awpag.domain.model.Parcelamento;
import com.example.awpag.domain.repository.ParcelamentoRepository;
import com.example.awpag.domain.service.ParcelamentoService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

      private final ParcelamentoRepository parcelamentoRepository;
      private final ParcelamentoService parcelamentoService;
      // private final ModelMapper modelMapper;
      private final ParcelamentoAssembler parcelamentoAssembler;

      // COM MODELMAPPER
      @GetMapping
      public List<ParcelamentoModel> listar() {
            return parcelamentoAssembler.toCollectionModel(parcelamentoRepository.findAll());
      }

      // SEM MODELMAPPER
      // @GetMapping
      // public List<Parcelamento> listar() {
      // return parcelamentoRepository.findAll();
      // }

      // COM REPRESENTATION MODEL (DTO) E COM MODELMAPPER
      @GetMapping("{parcelamentoId}")
      public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId) {
            return parcelamentoRepository.findById(parcelamentoId)
                        // .map(parcelamento -> modelMapper.map(parcelamento, ParcelamentoModel.class))
                        // .map(parcelamento -> parcelamentoAssembler.toModel(parcelamento))
                        .map(parcelamentoAssembler::toModel)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());

      }
      // // COM REPRESENTATION MODEL (DTO) E SEM MODELMAPPER
      // @GetMapping("{parcelamentoId}")
      // public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long
      // parcelamentoId) {
      // return parcelamentoRepository.findById(parcelamentoId)
      // .map(parcelamento -> {
      // var parcelamentoModel = new ParcelamentoModel();
      // parcelamentoModel.setId(parcelamento.getId());
      // parcelamentoModel.setNomeCliente(parcelamento.getCliente().getNome());
      // parcelamentoModel.setDescricao(parcelamento.getDescricao());
      // parcelamentoModel.setValorTotal(parcelamento.getValorTotal());
      // parcelamentoModel.setParcelas(parcelamento.getQuantidadeParcelas());
      // parcelamentoModel.setDataCriacao(parcelamento.getDataCriacao());

      // return ResponseEntity.ok(parcelamentoModel);
      // })
      // .orElse(ResponseEntity.notFound().build());

      // }

      // COM DOMAIN MODEL
      // @GetMapping("{parcelamentoId}")
      // public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId)
      // {
      // return parcelamentoRepository.findById(parcelamentoId)
      // // A linha abaixo é a mesma coisa que um tratamento de erro com IF.
      // // .map(p -> ResponseEntity.ok(p)) essa linha faz o mesmo que a de baixo
      // .map(ResponseEntity::ok)
      // .orElse(ResponseEntity.notFound().build());

      // }

      // COM MODELMAPPER NA SAÍDA E NA ENTRADA
      @PostMapping
      @ResponseStatus(HttpStatus.CREATED)
      public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoInputModel parcelamentoInputModel) {
            Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInputModel);
            Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);

            return parcelamentoAssembler.toModel(parcelamentoCadastrado);
      }

      // SEM MODELMAPPER
      // @PostMapping
      // @ResponseStatus(HttpStatus.CREATED)
      // public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento)
      // {
      // return parcelamentoService.cadastrar(parcelamento);
      // }

      // ESSE MÉTODO SE ENCONTRA NO APIEXCEPTIONHANDLER.JAVA
      // Esse método captura os error tratados no SERVICE(NegocioException) e trata o
      // mesmo enviando apenas uma menssagem no body.
      // @ExceptionHandler(NegocioException.class)
      // public ResponseEntity<String> capturarErro(NegocioException e) {
      // return ResponseEntity.badRequest().body(e.getMessage());
      // }
}
