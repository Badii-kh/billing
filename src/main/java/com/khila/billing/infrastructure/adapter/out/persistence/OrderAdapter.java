package com.khila.billing.infrastructure.adapter.out.persistence;

import com.khila.billing.domain.pricing.config.DomainService;
import com.khila.billing.domain.pricing.exception.OrderNotFoundException;
import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.domain.pricing.port.out.OrderRepositoryPort;
import com.khila.billing.infrastructure.adapter.out.persistence.entity.OrderEntity;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class OrderAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order findById(Long id) {
        return orderJpaRepository.findById(id)
                .map(OrderEntity::toModel)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
