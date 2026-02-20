package com.example.billing.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private Double price;

	@Column(name = "origin")
	private String origin;
	
	@ManyToOne
    @JoinColumn(name="type_id", nullable=false)
	private ProductType type;

}
