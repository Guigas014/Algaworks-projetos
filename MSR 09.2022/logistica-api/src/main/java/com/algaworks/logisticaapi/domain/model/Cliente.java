package com.algaworks.logisticaapi.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.algaworks.logisticaapi.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {
 
  //Atributtes
  @NotNull(groups = ValidationGroups.ClienteId.class)
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 60)
  private String nome;
  
  @NotBlank
  @Email
  @Size(max = 255)
  private String email;
  
  @NotBlank
  @Size(max =20)
  private String telefone;



  //Getter and Setter
  //public Long getId() {
    //return id;
  //}
  //public void setId(Long id) {
    //this.id = id;
  //}
  //public String getNome() {
    //return nome;
  //}
  //public void setNome(String nome) {
    //this.nome = nome;
  //}
  //public String getEmail() {
    //return email;
  //}
  //public void setEmail(String email) {
    //this.email = email;
  //}
  //public String getTelefone() {
    //return telefone;
  //}
  //public void setTelefone(String telefone) {
    //this.telefone = telefone;
  //}

}

