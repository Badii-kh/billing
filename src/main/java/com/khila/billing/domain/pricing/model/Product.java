package com.khila.billing.domain.pricing.model;

public record Product(String name, Double price, String origin, ProductType type) {

    private static final String ORIGIN_FRANCE = "FR";
    private static final double FOREIGN_TAX = 0.05;

    public double computeTax() {
        return type.tax() + (origin.equals(ORIGIN_FRANCE) ? 0 : FOREIGN_TAX);
    }
}
