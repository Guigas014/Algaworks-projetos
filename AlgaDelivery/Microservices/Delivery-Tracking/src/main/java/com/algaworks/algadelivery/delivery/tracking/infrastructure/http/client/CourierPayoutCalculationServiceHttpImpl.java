package com.algaworks.algadelivery.delivery.tracking.infrastructure.http.client;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.algaworks.algadelivery.delivery.tracking.domain.service.CourierPayoutCalculationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService {

      private final CourierAPIClient courierAPIClient;

      @Override
      public BigDecimal calculatePayout(Double distanceInKm) {
            var courierPayoutResultModel = courierAPIClient.payoutCalculation(
                        new CourierPayoutCalculationInput(distanceInKm));

            return courierPayoutResultModel.getPayoutFee();
      }

}
