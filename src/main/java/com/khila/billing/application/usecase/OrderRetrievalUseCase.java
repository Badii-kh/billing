package com.khila.billing.application.usecase;

import com.khila.billing.domain.pricing.model.Order;

public interface OrderRetrievalUseCase {

    Order retrieve(long orderId);
}
