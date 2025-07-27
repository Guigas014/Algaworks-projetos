package com.algaworks.algadelivery.delivery.tracking.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.algaworks.algadelivery.delivery.tracking.domain.exception.DomainException;

class DeliveryTest {

      @Test
      public void shouldChangeToPlaced() {
            Delivery delivery = Delivery.draft();

            delivery.editPreparationDetails(createdValidPreparationDetails());

            delivery.place();

            assertEquals(DeliveryStatus.WAITING_FOR_COURIER, delivery.getStatus());
            assertNotNull(delivery.getPlacedAt());
      }

      @Test
      public void shouldNotPlaced() {
            Delivery delivery = Delivery.draft();

            // delivery.editPreparationDetails(createdValidPreparationDetails());

            // delivery.place();

            assertThrows(DomainException.class, () -> {
                  delivery.place();
            });

            assertEquals(DeliveryStatus.DRAFT, delivery.getStatus());
            assertNull(delivery.getPlacedAt());
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
