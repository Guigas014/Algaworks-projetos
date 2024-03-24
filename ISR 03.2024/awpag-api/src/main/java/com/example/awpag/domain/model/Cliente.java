package com.example.awpag.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
// @Table(name = "tb_cliente")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @EqualsAndHashCode.Include
      private Long id;

      @NotBlank
      @Size(max = 60)
      private String nome;

      @NotBlank
      @Size(max = 255)
      @Email
      private String email;

      @NotBlank
      @Size(max = 20)
      // @Column(name = "phone")
      private String telefone;
}
