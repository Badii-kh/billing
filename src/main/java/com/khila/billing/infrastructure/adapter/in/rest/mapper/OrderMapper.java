package com.khila.billing.infrastructure.adapter.in.rest.mapper;

import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.domain.pricing.model.OrderItem;
import com.khila.billing.domain.pricing.model.Product;
import com.khila.billing.domain.pricing.model.ProductType;
import com.khila.billing.infrastructure.adapter.in.rest.dto.OrderDto;
import com.khila.billing.infrastructure.adapter.in.rest.dto.OrderItemDto;
import com.khila.billing.infrastructure.adapter.in.rest.dto.ProductDto;
import com.khila.billing.infrastructure.adapter.in.rest.dto.ProductTypeDto;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderMapper implements Function<Order, OrderDto> {

    @Override
    public OrderDto apply(Order in) {
        OrderDto out = new OrderDto();
        out.setItems(in.getItems().stream()
                .map(this::mapOrderItem)
                .collect(Collectors.toSet()));
        out.setTotalTTC(in.getTotalTTC());
        out.setTotalTax(in.getTotalTax());
        return out;
    }

    private OrderItemDto mapOrderItem(OrderItem in) {
        OrderItemDto out = new OrderItemDto();
        out.setQuantity(in.quantity());
        out.setProduct(mapProduct(in.product()));
        return out;
    }

    private ProductDto mapProduct(Product in) {
        ProductDto out = new ProductDto();
        out.setName(in.name());
        out.setPrice(in.price());
        out.setOrigin(in.origin());
        out.setType(mapProductType(in.type()));
        return out;
    }

    private ProductTypeDto mapProductType(ProductType in) {
        ProductTypeDto out = new ProductTypeDto();
        out.setLabel(in.label());
        out.setTax(in.tax());
        return out;
    }

}
