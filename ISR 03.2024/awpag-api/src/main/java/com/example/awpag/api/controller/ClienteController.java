package com.example.awpag.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.awpag.domain.model.Cliente;
import com.example.awpag.domain.repository.ClienteRepository;
import com.example.awpag.domain.service.CadastroClienteService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

      // O Autowired é subtituido pelo CONSTRUTOR criado pelo Lombok
      // (AllArgsConstructor)
      // final - a variável não muda mais de valor.
      // @Autowired
      private final ClienteRepository clienteRepository;
      private final CadastroClienteService cadastroClienteService;

      // SEGUNDO MÉTODO: PERSISNTÊNCIA
      // @PersistenceContext
      // private EntityManager manager;

      @GetMapping
      public List<Cliente> listar() {
            return clienteRepository.findAll();
            // return clienteRepository.findByNome("Jane");

            // SEGUNDO MÉTODO: USANDO JAKARTE PERSISNTECE PARA ACEESAR O DB.
            // return manager.createQuery("from Cliente", Cliente.class).getResultList();

            // PRIMEIRO MÉTODO: CRIANDO CLIENTE DA MANEIRA MAIS SIMPLES POSSÍVEL.
            // var cliente1 = new Cliente();
            // cliente1.setId(1L);
            // cliente1.setNome("José");
            // cliente1.setEmail("jose@gmail.com");
            // cliente1.setTelefone("33554466");

            // var cliente2 = new Cliente();
            // cliente2.setId(2L);
            // cliente2.setNome("Jane");
            // cliente2.setEmail("jane@gmail.com");
            // cliente2.setTelefone("66445533");

            // return Arrays.asList(cliente1, cliente2);
            // INSERT INTO cliente (nome, email, telefone) VALUES ('José', 'jose@gmail.com',
            // '33554466')
      }

      @GetMapping("/{clienteId}")
      public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);

            if (cliente.isPresent()) {
                  return ResponseEntity.ok(cliente.get());
            }

            return ResponseEntity.notFound().build();
      }

      // O @Valid é usado quando uma validação (ex. @NotBlank) é feita no model.
      @ResponseStatus(HttpStatus.CREATED) // usado quando não é possível o tratamento dos dados
      @PostMapping
      public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
            // COM SERVICE
            return cadastroClienteService.salvar(cliente);

            // SEM SERVICE
            // return clienteRepository.save(cliente);
      }

      @PutMapping("/{clienteId}")
      public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente) {
            if (!clienteRepository.existsById(clienteId)) {
                  return ResponseEntity.notFound().build();
            }

            cliente.setId(clienteId);

            // COM SERVICE
            Cliente clienteAtualizado = cadastroClienteService.salvar(cliente);

            // SEM SERVICE
            // Cliente clienteAtualizado = clienteRepository.save(cliente);

            return ResponseEntity.ok(clienteAtualizado);
      }

      @DeleteMapping("/{clienteId}")
      public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
            if (!clienteRepository.existsById(clienteId)) {
                  return ResponseEntity.notFound().build();
            }

            // COM SERVICE
            cadastroClienteService.excluir(clienteId);

            // SEM SERVICE
            // clienteRepository.deleteById(clienteId);

            return ResponseEntity.noContent().build();
      }

      // ESSE MÉTODO SE ENCONTRA NO APIEXCEPTIONHANDLER.JAVA
      // Esse método captura os error tratados no SERVICE(NegocioException) e trata o
      // mesmo enviando apenas uma menssagem no body.
      // @ExceptionHandler(NegocioException.class)
      // public ResponseEntity<String> capturarErro(NegocioException e) {
      // return ResponseEntity.badRequest().body(e.getMessage());
      // }
}
