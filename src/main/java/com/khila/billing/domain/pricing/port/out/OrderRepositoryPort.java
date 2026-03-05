package com.khila.billing.domain.pricing.port.out;

import com.khila.billing.domain.pricing.model.Order;

public interface OrderRepositoryPort {
    Order findById(Long id);
}
