package com.khila.billing.infrastructure.adapter.out.persistence.entity;


import com.khila.billing.domain.pricing.model.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItemEntity {

	@Id
	private long id;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
	private ProductEntity product;

	@Column(name = "quantity", nullable=false)
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private OrderEntity itemOrder;

    public OrderItem toModel() {
        return new OrderItem(product.toModel(), quantity);
    }

}
