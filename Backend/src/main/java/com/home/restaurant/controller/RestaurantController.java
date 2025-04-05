package com.home.restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.restaurant.constants.RestApiPaths;
import com.home.restaurant.dto.model.RestaurantDTO;
import com.home.restaurant.service.interfaces.RestaurantService;

@RestController
@RequestMapping(RestApiPaths.Restaurants.BASE)
public class RestaurantController {

	private RestaurantService restaurantService;

	RestaurantController(RestaurantService restaurantService) {
		this.restaurantService = restaurantService;
	}

	@GetMapping(RestApiPaths.Restaurants.RESTAURANT_BY_ID)
	public RestaurantDTO getRestaurantById(@PathVariable(RestApiPaths.PathVariables.ID) Long id) {
		RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
		return restaurant;
	}
}
