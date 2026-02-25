package com.khila.billing.domain.pricing.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class OrderTest {

    @Test
    void buildOrder_test_fr_tax() {
        ProductType productType1=new ProductType("book", 0.1);
        Product product1=new Product("livre", 12.49, "FR", productType1);
        OrderItem orderItem1=new OrderItem(product1, 2);
        ProductType productType2=new ProductType("basic", 0.);
        Product product2=new Product("barre de chocolat", 0.85, "FR", productType2);
        OrderItem orderItem2=new OrderItem(product2, 3);
        ProductType productType3=new ProductType("Other", 0.2);
        Product product3=new Product("CD musical", 14.99, "FR", productType3);
        OrderItem orderItem3=new OrderItem(product3, 1);

        Order order=new Order(new HashSet<>(List.of(orderItem1, orderItem2, orderItem3)));

        assertEquals(48.05, order.getTotalTTC());
        assertEquals(5.496, order.getTotalTax());
    }

    @Test
    void buildOrder_test_forein_tax() {
        ProductType productType1=new ProductType("Basic", 0.);
        Product product1=new Product("boîte de chocolat importée type 1", 10., "SW", productType1);
        OrderItem orderItem1=new OrderItem(product1, 2);
        ProductType productType2=new ProductType("basic", 0.2);
        Product product2=new Product("flacon de parfum importé type 1", 47.5, "GR", productType2);
        OrderItem orderItem2=new OrderItem(product2, 3);
        Order order=new Order(new HashSet<>(List.of(orderItem1, orderItem2)));

        assertEquals(199.15, order.getTotalTTC());
        assertEquals(36.625, order.getTotalTax());
    }

    @Test
    void buildOrder_test_fr_foreign_tax() {
        ProductType productType1=new ProductType("basic", 0.);
        Product product1=new Product("boîte de chocolat importée type 2", 11.25, "SW", productType1);
        OrderItem orderItem1=new OrderItem(product1, 2);
        ProductType productType2=new ProductType("other", 0.2);
        Product product2=new Product("flacon de parfum", 18.99, "FR", productType2);
        OrderItem orderItem2=new OrderItem(product2, 1);
        ProductType productType3=new ProductType("basic", 0.);
        Product product3=new Product("boîte de pilule contre la migraine", 9.75, "FR", productType3);
        OrderItem orderItem3=new OrderItem(product3, 3);
        ProductType productType4=new ProductType("other", 0.2);
        Product product4=new Product("flacon de parfum importé type 2", 27.99, "GR", productType4);
        OrderItem orderItem4=new OrderItem(product4, 2);

        Order order=new Order(new HashSet<>(List.of(orderItem1, orderItem2, orderItem3, orderItem4)));

        assertEquals(145.70000000000002, order.getTotalTTC());
        assertEquals(18.918, order.getTotalTax());
    }
}