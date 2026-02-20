package com.example.billing.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Table(name = "command_item")
@Getter
@Setter
public class CommandItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
	private Product product;

	@Column(name = "quantity")
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name="command_id", nullable=false)
	private Command command;
	
}
