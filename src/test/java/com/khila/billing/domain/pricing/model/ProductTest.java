package com.khila.billing.domain.pricing.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void computeTax_shouldReturnDomesticTax_whenOriginIsFrance() {
        Product p = new Product("Laptop", 1000.0, "FR", new ProductType("basic", 0.));

        double tax = p.computeTax();

        assertEquals(0., tax, 1e-6, "Tax for French product should be only type tax");
    }

    @Test
    void computeTax_shouldReturnForeignTax_whenOriginIsNotFrance() {
        Product p = new Product("Phone", 800.0, "US", new ProductType("basic", 0.));

        double tax = p.computeTax();

        assertEquals(0. + 0.05, tax, 1e-6, "Tax for foreign product should include extra 0.05");
    }

    @Test
    void computeTax_shouldWorkWithReducedTax() {
        Product p = new Product("Book", 30.0, "DE", new ProductType("book", 0.1));

        double tax = p.computeTax();

        assertEquals(0.1 + 0.05, tax, 1e-6, "Foreign product with reduced tax should add 0.05");
    }
}
