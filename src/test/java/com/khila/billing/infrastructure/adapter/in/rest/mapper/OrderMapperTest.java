package com.khila.billing.infrastructure.adapter.in.rest.mapper;

import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.domain.pricing.model.OrderItem;
import com.khila.billing.domain.pricing.model.Product;
import com.khila.billing.domain.pricing.model.ProductType;
import com.khila.billing.infrastructure.adapter.in.rest.dto.OrderDto;
import com.khila.billing.infrastructure.adapter.in.rest.dto.OrderItemDto;
import com.khila.billing.infrastructure.adapter.in.rest.dto.ProductDto;
import com.khila.billing.infrastructure.adapter.in.rest.dto.ProductTypeDto;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OrderMapperTest {

    private final OrderMapper mapper = new OrderMapper();

    @Test
    void shouldMapOrderToOrderDto() {
        // GIVEN
        ProductType type = new ProductType("ELECTRONICS", 20.);
        Product product = new Product("Laptop", 999.99, "CN", type);
        OrderItem item = new OrderItem(product, 2);

        Order order = new Order(
                Set.of(item)
        );

        // WHEN
        OrderDto dto = mapper.apply(order);

        // THEN
        assertThat(dto).isNotNull();
        assertThat(dto.getItems()).hasSize(1);

        // Item
        OrderItemDto dtoItem = dto.getItems().iterator().next();
        assertThat(dtoItem.getQuantity()).isEqualTo(2);

        // Product
        ProductDto dtoProduct = dtoItem.getProduct();
        assertThat(dtoProduct.getName()).isEqualTo("Laptop");
        assertThat(dtoProduct.getPrice()).isEqualTo(999.99);
        assertThat(dtoProduct.getOrigin()).isEqualTo("CN");

        // Product type
        ProductTypeDto dtoType = dtoProduct.getType();
        assertThat(dtoType.getLabel()).isEqualTo("ELECTRONICS");
        assertThat(dtoType.getTax()).isEqualTo(20);

        // Totaux
        assertThat(dto.getTotalTTC()).isEqualTo(42099.6);
        assertThat(dto.getTotalTax()).isEqualTo(40099.599);
    }
}