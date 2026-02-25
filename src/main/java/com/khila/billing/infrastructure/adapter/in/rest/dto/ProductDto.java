package com.khila.billing.infrastructure.adapter.in.rest.dto;

import lombok.Data;

@Data
public class ProductDto {

	private String name;
	private double price;
	private String origin;
	private ProductTypeDto type;
}
