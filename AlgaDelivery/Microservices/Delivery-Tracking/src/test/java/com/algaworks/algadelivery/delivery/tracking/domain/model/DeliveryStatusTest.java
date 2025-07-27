package com.algaworks.algadelivery.delivery.tracking.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DeliveryStatusTest {

      @Test
      void draft_canChangeToWaitingForCourier() {
            assertTrue(
                        DeliveryStatus.DRAFT.canChangeTo(DeliveryStatus.WAITING_FOR_COURIER));
      }

      @Test
      void draft_canChangeToIntransit() {
            assertTrue(
                        DeliveryStatus.DRAFT.canNotChangeTo(DeliveryStatus.IN_TRANSIT));
      }

}
