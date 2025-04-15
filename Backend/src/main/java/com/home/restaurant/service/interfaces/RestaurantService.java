package com.home.restaurant.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.home.restaurant.dto.model.RestaurantDTO;

public interface RestaurantService {
	RestaurantDTO getRestaurantById(Long id);
	Page<RestaurantDTO>  getRestaurants(PageRequest request);
}
