package com.example.awpag.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// Essa classe é o Representation Model do Cliente na entrada. Pedindo apenas o
// id do cliente.
public class ClienteIdInput {

      @NotNull
      private Long id;
}
