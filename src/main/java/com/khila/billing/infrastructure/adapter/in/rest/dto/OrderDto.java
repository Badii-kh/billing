package com.khila.billing.infrastructure.adapter.in.rest.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDto {

	private Set<OrderItemDto> items;
	private double totalTTC = 0.0;
	private double totalTax = 0.0;
}
