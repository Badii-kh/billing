package com.khila.billing.infrastructure.adapter.out.persistence.entity;


import com.khila.billing.domain.pricing.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity {
	
	@Id
	private long id;
	
	@Column(name = "name", nullable=false)
	private String name;

	@Column(name = "price", nullable=false, precision = 19, scale = 4)
	private BigDecimal price;

	@Column(name = "origin", nullable=false)
	private String origin;
	
	@ManyToOne
    @JoinColumn(name="type_id", nullable=false)
	private ProductTypeEntity type;

    public Product toModel() {
        return new Product(name, price.doubleValue(), origin, type.toModel());
    }

}
