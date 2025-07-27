package com.algaworks.algadelivery.delivery.tracking.domain.model;

import java.util.Arrays;
import java.util.List;

public enum DeliveryStatus {
      DRAFT,
      WAITING_FOR_COURIER(DRAFT),
      IN_TRANSIT(WAITING_FOR_COURIER),
      DELIVERED(IN_TRANSIT);

      // Atributte
      private final List<DeliveryStatus> previousStatuses;

      // Constructor
      DeliveryStatus(DeliveryStatus... previousStatuses) {
            this.previousStatuses = Arrays.asList(previousStatuses);
      }

      // Methods
      public boolean canNotChangeTo(DeliveryStatus newStatus) {
            DeliveryStatus currentStatus = this;

            return !newStatus.previousStatuses.contains(currentStatus);
      }

      public boolean canChangeTo(DeliveryStatus newStatus) {
            return !canNotChangeTo(newStatus);
      }
}
