package com.algaworks.algadelivery.courier.management.infrastructure.event;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryFulfilledIntegrationEvent {

      private OffsetDateTime occuredAt;
      private UUID deliveryId;
}
