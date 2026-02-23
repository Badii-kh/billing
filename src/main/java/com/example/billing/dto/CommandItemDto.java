package com.example.billing.dto;

import lombok.Data;

@Data
public class CommandItemDto {

	private long id;
	private int quantity;
	private ProductDto product;
}
