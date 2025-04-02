package com.home.restaurant.service.interfaces;

import java.util.List;

import com.home.restaurant.dto.model.MenuDTO;

public interface MenuService {
	public List<MenuDTO> getMenusByRestaurantId(Long restaurantId);
}
