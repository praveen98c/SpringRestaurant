package com.home.restaurant.dto.model;

public class RestaurantDTO {
	private Long id;
	private String name;
	private String location;
	private String imageUrl;

	public RestaurantDTO(Long id, String name, String location, String imageUrl) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.imageUrl  = imageUrl;
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

	public String getImageUrl() {
		return imageUrl;
	}
}
