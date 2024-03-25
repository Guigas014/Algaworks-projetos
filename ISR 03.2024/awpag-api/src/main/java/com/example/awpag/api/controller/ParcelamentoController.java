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

      @GetMapping
      public List<Parcelamento> listar() {
            return parcelamentoRepository.findAll();
      }

      @GetMapping("{parcelamentoId}")
      public ResponseEntity<Parcelamento> buscar(@PathVariable Long parcelamentoId) {
            return parcelamentoRepository.findById(parcelamentoId)
                        // E isso tudo abaixo é a mesma coisa que um IF.
                        // .map(p -> ResponseEntity.ok(p)) essa linha faz o mesmo que a de baixo
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());

      }

      @PostMapping
      @ResponseStatus(HttpStatus.CREATED)
      public Parcelamento cadastrar(@Valid @RequestBody Parcelamento parcelamento) {
            return parcelamentoService.cadastrar(parcelamento);
      }

      // ESSE MÉTODO SE ENCONTRA NO APIEXCEPTIONHANDLER.JAVA
      // Esse método captura os error tratados no SERVICE(NegocioException) e trata o
      // mesmo enviando apenas uma menssagem no body.
      // @ExceptionHandler(NegocioException.class)
      // public ResponseEntity<String> capturarErro(NegocioException e) {
      // return ResponseEntity.badRequest().body(e.getMessage());
      // }
}
