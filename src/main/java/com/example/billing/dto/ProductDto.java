package com.example.billing.dto;

import lombok.Data;

@Data
public class ProductDto {

	private long id;
	private String name;
	private double price;
	private String origin;
	private ProductTypeDto type;
}
