package com.home.restaurant.service.interfaces;

import java.util.List;

import com.home.restaurant.dto.model.FoodItemDTO;

public interface FoodItemService {
	public List<FoodItemDTO> getFoodItemsByMenuId(Long menuId);
}
