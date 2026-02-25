package com.khila.billing.domain.pricing.exception;

import lombok.Getter;

public class OrderNotFoundException extends RuntimeException {

    @Getter
    private final long orderId;

    public OrderNotFoundException(long orderId){
        super("The order with ID "+ orderId +" was not found");
        this.orderId = orderId;
    }

}
