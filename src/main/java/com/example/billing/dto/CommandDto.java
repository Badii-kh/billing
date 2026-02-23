package com.example.billing.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CommandDto {

	private long id;
	private Set<CommandItemDto> items;
	private double totalTTC = 0.0;
	private double totalTax = 0.0;
}
