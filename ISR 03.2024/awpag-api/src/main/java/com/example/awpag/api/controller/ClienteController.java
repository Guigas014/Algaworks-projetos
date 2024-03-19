package com.example.awpag.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.awpag.domain.model.Cliente;

@RestController
public class ClienteController {

      @GetMapping("/clientes")
      public List<Cliente> listar() {

            var cliente1 = new Cliente();
            cliente1.setId(1L);
            cliente1.setNome("José");
            cliente1.setEmail("jose@gmail.com");
            cliente1.setTelefone("33554466");

            var cliente2 = new Cliente();
            cliente2.setId(2L);
            cliente2.setNome("Jane");
            cliente2.setEmail("jane@gmail.com");
            cliente2.setTelefone("66445533");

            return Arrays.asList(cliente1, cliente2);
      }
}
