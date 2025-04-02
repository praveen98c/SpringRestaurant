package com.home.restaurant.service;

import org.springframework.stereotype.Service;

import com.home.restaurant.dto.model.RestaurantDTO;
import com.home.restaurant.model.Restaurant;
import com.home.restaurant.repository.RestaurantRepository;
import com.home.restaurant.service.interfaces.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantRepository restaurantRepository;
	
	RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
		this.restaurantRepository = restaurantRepository;
	}
	
	public RestaurantDTO getRestaurantById(Long id) {
		Restaurant restaurant = restaurantRepository.getReferenceById(id);
		return new RestaurantDTO(restaurant.getId(), restaurant.getName(), restaurant.getLocation());
	}
}
