package com.example.awpag.domain.exception;

//Essa classe trata o erro do SERVICE dentro do controller através do ApiExceptionHandler.
public class NegocioException extends RuntimeException {

      public NegocioException(String message) {
            super(message);
      }
}
