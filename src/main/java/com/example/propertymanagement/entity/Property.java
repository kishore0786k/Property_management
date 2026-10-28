package com.example.propertymanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Property name is required")
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "Location is required")
	@Column(nullable = false)
	private String location;

	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than zero")
	@Column(nullable = false)
	private Double price;

	@NotBlank(message = "Property type is required")
	@Column(nullable = false)
	private String type;

	@NotBlank(message = "Property status is required")
	@Column(nullable = false)
	private String status;
	public Property() {
	}
	public Property(String name, String location, Double price, String type, String status) {
		super();
		this.name = name;
		this.location = location;
		this.price = price;
		this.type = type;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
