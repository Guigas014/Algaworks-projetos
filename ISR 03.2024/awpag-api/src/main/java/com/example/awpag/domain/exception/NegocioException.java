package com.example.awpag.domain.exception;

//Essa classe trata o erro dentro do SERVICE.
public class NegocioException extends RuntimeException {

      public NegocioException(String message) {
            super(message);
      }
}
