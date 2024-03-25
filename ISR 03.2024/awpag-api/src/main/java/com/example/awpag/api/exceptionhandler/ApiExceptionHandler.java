package com.example.awpag.api.exceptionhandler;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.awpag.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

//Essa classe captura os Exceptions de todos os controllers e formato os erros de acordo com a RFC 7807. É uma boa prática fazer essa classe.
@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

      // Usamos esse instância para utilizar os dados do arquivo messages.properties
      private final MessageSource messageSource;

      // Esse método é criado quando digitamos o nome dele. E depois nós customizamos
      // o mesmo.
      @Override
      protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

            // Criamos um problemDetail para um único status (nesse caso 400) e alteramos as
            // prorpiedades
            // necessárias.
            ProblemDetail problemDetail = ProblemDetail.forStatus(status);
            problemDetail.setTitle("Um ou mais campos estão inválidos.");
            problemDetail.setType(URI.create("https://uri/com/a/documentacao/da/api"));

            // Pega o campo específico do erro que foi tratado, por exemplo: nome
            var fields = ex.getBindingResult().getAllErrors().stream()
                        .collect(Collectors.toMap(error -> ((FieldError) error).getField(),
                                    error -> messageSource.getMessage(error, LocaleContextHolder.getLocale())));
            // .collect(Collectors.toMap(error -> ((FieldError) error).getField(),
            // DefaultMessageSourceResolvable::getDefaultMessage));

            problemDetail.setProperty("fields", fields);

            return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
            // return super.handleMethodArgumentNotValid(ex, problemDetail, headers, status,
            // request);
      }

      // Esse método estava nos controllers. E o próximo método abaixo é esse mesmo
      // método, porém com modificações.
      // @ExceptionHandler(NegocioException.class)
      // public ResponseEntity<String> capturarErro(NegocioException e) {
      // return ResponseEntity.badRequest().body(e.getMessage());
      // }

      @ExceptionHandler(NegocioException.class)
      public ProblemDetail handleNegocio(NegocioException e) {
            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
            problemDetail.setTitle(e.getMessage());
            problemDetail.setType(URI.create("https://uri/com/a/documentacao/da/api"));

            return problemDetail;
      }

      // Esse método modifica o tratamento dos erros como: apagar um cliente que está
      // sendo usado por outro recurso (parcelamento) FK.
      @ExceptionHandler(DataIntegrityViolationException.class)
      public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException e) {
            ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
            problemDetail.setTitle("Recurso está em uso");
            problemDetail.setType(URI.create("https://uri/com/a/documentacao/da/api"));

            return problemDetail;
      }

}
