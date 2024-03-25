package com.example.awpag.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.awpag.domain.exception.NegocioException;
import com.example.awpag.domain.model.Cliente;
import com.example.awpag.domain.model.Parcelamento;
import com.example.awpag.domain.repository.ParcelamentoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ParcelamentoService {

      private final ParcelamentoRepository parcelamentoRepository;
      private final CadastroClienteService cadastroClienteService;

      @Transactional
      public Parcelamento cadastrar(Parcelamento novoParcelamento) {
            if (novoParcelamento.getId() != null) {
                  throw new NegocioException("Parcelamento a ser criado não deve possuir um código!");
            }

            Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());

            novoParcelamento.setCliente(cliente);
            novoParcelamento.setDataCriacao(OffsetDateTime.now());

            return parcelamentoRepository.save(novoParcelamento);
      }
}
