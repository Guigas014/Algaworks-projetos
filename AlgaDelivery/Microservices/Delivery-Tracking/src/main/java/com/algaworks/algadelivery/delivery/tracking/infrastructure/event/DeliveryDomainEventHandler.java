package com.algaworks.algadelivery.delivery.tracking.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algadelivery.delivery.tracking.domain.event.DeliveryFulfilledEvent;
import com.algaworks.algadelivery.delivery.tracking.domain.event.DeliveryPickUpEvent;
import com.algaworks.algadelivery.delivery.tracking.domain.event.DeliveryPlacedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDomainEventHandler {

      private final IntegrationEventPublisher integrationEventPublisher;

      public static final String deliveryEventsTopicName = "deliveries.v1.events";

      @EventListener
      public void handle(DeliveryPlacedEvent event) {
            log.info(event.toString());
            integrationEventPublisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
      }

      @EventListener
      public void handle(DeliveryPickUpEvent event) {
            log.info(event.toString());
            integrationEventPublisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
      }

      @EventListener
      public void handle(DeliveryFulfilledEvent event) {
            log.info(event.toString());
            integrationEventPublisher.publish(event, event.getDeliveryId().toString(), deliveryEventsTopicName);
      }
}
