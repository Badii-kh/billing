package com.khila.billing.application.service;

import com.khila.billing.application.config.ApplicationService;
import com.khila.billing.application.usecase.OrderRetrievalUseCase;
import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.domain.pricing.port.out.OrderRepositoryPort;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class OrderService implements OrderRetrievalUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    @Override
    public Order retrieve(long orderId) {
        return orderRepositoryPort.findById(orderId);
    }
}
