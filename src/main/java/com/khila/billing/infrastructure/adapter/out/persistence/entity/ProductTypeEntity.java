package com.khila.billing.infrastructure.adapter.out.persistence.entity;


import com.khila.billing.domain.pricing.model.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product_type")
@Getter
@Setter
public class ProductTypeEntity {

	@Id
	private long id;
	
	@Column(name = "label", nullable=false)
	private String label;

	@Column(name = "tax", nullable=false, precision = 19, scale = 4)
	private BigDecimal tax;

    public ProductType toModel(){
        return new ProductType(label, tax.doubleValue());
    }
	
}
