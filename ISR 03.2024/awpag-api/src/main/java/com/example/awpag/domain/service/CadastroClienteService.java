package com.example.awpag.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.awpag.domain.exception.NegocioException;
import com.example.awpag.domain.model.Cliente;
import com.example.awpag.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CadastroClienteService {

      private final ClienteRepository clienteRepository;

      @Transactional
      public Cliente salvar(Cliente cliente) {
            // O filter testa se o cliente do DB (c) não é o mesmo cliente que vem do front.
            // Para esse filter funcionar devemos implementar o HASH CODE na entidade.
            boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                        .filter(c -> !c.equals(cliente))
                        .isPresent();

            if (emailEmUso) {
                  throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
            }

            return clienteRepository.save(cliente);
      }

      @Transactional
      public void excluir(Long clienteId) {
            clienteRepository.deleteById(clienteId);
      }

}
