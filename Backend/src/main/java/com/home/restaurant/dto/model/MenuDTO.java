package com.home.restaurant.dto.model;

import com.home.restaurant.model.Menu;

public class MenuDTO {
	private String name;
	private String description;
	
	public MenuDTO(Menu menu) {
		this.name = menu.getName();
		this.description = menu.getDescription();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
