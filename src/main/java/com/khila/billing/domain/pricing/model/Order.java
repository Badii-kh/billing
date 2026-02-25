package com.khila.billing.domain.pricing.model;


import com.khila.billing.domain.pricing.util.TaxUtil;
import lombok.Getter;

import java.util.Set;

@Getter
public class Order {

    private final Set<OrderItem> items;
    private double totalTTC = 0.0;
    private double totalTax = 0.0;

    public Order(Set<OrderItem> items) {
        this.items = items;
        buildOrderDetail();
    }

    private void buildOrderDetail() {
        for(OrderItem item : items) {
            double itemPriceHT = item.product().price() * item.quantity();
            double itemTax = itemPriceHT * item.product().computeTax();
            double itemPriceTTC = TaxUtil.roundToHigherCent(itemPriceHT + itemTax);
            totalTTC += itemPriceTTC;
            totalTax += itemTax;
        }
    }
}
