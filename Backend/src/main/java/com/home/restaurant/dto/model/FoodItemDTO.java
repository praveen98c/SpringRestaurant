package com.home.restaurant.dto.model;

import java.math.BigDecimal;

import com.home.restaurant.model.FoodItem;
import com.home.restaurant.model.enums.Cuisine;

public class FoodItemDTO {

	private String name;
	private String description;
	private BigDecimal price;
	private BigDecimal ratings;
	private Cuisine cuisine;
	
	public FoodItemDTO(FoodItem foodItem) {
		name = foodItem.getName();
		description = foodItem.getDescription();
		price = foodItem.getPrice();
		ratings = foodItem.getRating();
		cuisine = foodItem.getCuisine();
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getRatings() {
		return ratings;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}
}
