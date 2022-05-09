package com.example.billing.dto;

import lombok.Data;

@Data
public class ProductTypeDto {

	private long id;
	private String label;
	private double tax;
}
