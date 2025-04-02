package com.home.restaurant.dto.model;

public class RestaurantDTO {
	private Long id;
	private String name;
	private String location;

	public RestaurantDTO(Long id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
}
