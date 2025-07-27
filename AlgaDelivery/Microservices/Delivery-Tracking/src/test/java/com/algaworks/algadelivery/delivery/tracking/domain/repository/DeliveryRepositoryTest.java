package com.algaworks.algadelivery.delivery.tracking.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.algaworks.algadelivery.delivery.tracking.domain.model.ContactPoint;
import com.algaworks.algadelivery.delivery.tracking.domain.model.Delivery;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeliveryRepositoryTest {

      @Autowired
      private DeliveryRepository deliveryRepository;

      @Test
      public void shouldPersist() {
            Delivery delivery = Delivery.draft();

            delivery.editPreparationDetails(createdValidPreparationDetails());

            delivery.addItem("Computador", 2);
            delivery.addItem("Notebook", 2);

            deliveryRepository.saveAndFlush(delivery);

            Delivery persistedDelivery = deliveryRepository.findById(delivery.getId()).orElseThrow();

            assertEquals(2, persistedDelivery.getItems().size());

      }

      private Delivery.PreparationDetails createdValidPreparationDetails() {
            ContactPoint sender = ContactPoint.builder()
                        .zipCode("12345-678")
                        .street("Rua Jacaranda")
                        .number("123")
                        .complement("Apto 1")
                        .name("João da Silva")
                        .phone("(61) 99555-1234")
                        .build();

            ContactPoint recipient = ContactPoint.builder()
                        .zipCode("87654-321")
                        .street("Rua Ipê")
                        .number("321")
                        .complement("")
                        .name("Maria da Silva")
                        .phone("(61) 99777-1234")
                        .build();

            return Delivery.PreparationDetails.builder()
                        .sender(sender)
                        .recipient(recipient)
                        .distanceFee(new BigDecimal(15.00))
                        .courierPayout(new BigDecimal(5.00))
                        .expectedDeliveryTime(Duration.ofHours(5))
                        .build();
      }
}
