package com.algaworks.algadelivery.delivery.tracking.domain.service;

import com.algaworks.algadelivery.delivery.tracking.domain.model.ContactPoint;

// Essa classe é ficticia, pois deveriamos usar uma api para obter esses dados.
// Ex: Google Maps
public interface DeliveryTimeEstimationService {
      DeliveryEstimate estimate(ContactPoint sender, ContactPoint receiver);
}
