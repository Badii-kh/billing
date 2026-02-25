package com.khila.billing.infrastructure.adapter.in.rest.dto;

import lombok.Data;

@Data
public class OrderItemDto {

	private int quantity;
	private ProductDto product;
}
