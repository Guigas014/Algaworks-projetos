package com.example.awpag.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.awpag.api.model.ParcelamentoInputModel;
import com.example.awpag.api.model.ParcelamentoModel;
import com.example.awpag.domain.model.Parcelamento;

import lombok.AllArgsConstructor;

// Essa classe só é usada com o ModelMapper, para que o ModelMapper não seja utilizado no CONTROLLER.
@AllArgsConstructor
@Component
public class ParcelamentoAssembler {

      private final ModelMapper modelMapper;

      // Transforma do DOMAIN-MODEL(MODEL) para o REPRESENTATION-MODEL(DTO)
      public ParcelamentoModel toModel(Parcelamento parcelamento) {
            return modelMapper.map(parcelamento, ParcelamentoModel.class);
      }

      // Transforma uma lista de elementos do DOMAIN-MODEL para uma lista de elementos
      // do REPRESENTATION-MODEL.
      public List<ParcelamentoModel> toCollectionModel(List<Parcelamento> parcelamentos) {
            return parcelamentos.stream().map(this::toModel).toList();
      }

      // Transforma do REPRESENTATION-MODEL(DTO) para o DOMAIN-MODEL(MODEL)
      public Parcelamento toEntity(ParcelamentoInputModel parcelamentoInputModel) {
            return modelMapper.map(parcelamentoInputModel, Parcelamento.class);
      }

}
