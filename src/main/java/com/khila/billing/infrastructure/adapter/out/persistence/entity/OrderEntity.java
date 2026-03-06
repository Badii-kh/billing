package com.khila.billing.infrastructure.adapter.out.persistence.entity;

import com.khila.billing.domain.pricing.model.Order;
import com.khila.billing.domain.pricing.model.OrderItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

	@Id
	private long id;

	@OneToMany(mappedBy = "itemOrder")
	private Set<OrderItemEntity> items;

    public Order toModel(){
        Set<OrderItem> modelItems =
                items.stream()
                        .map(OrderItemEntity::toModel)
                        .collect(Collectors.toSet());
        return new Order(modelItems);
    }

}
