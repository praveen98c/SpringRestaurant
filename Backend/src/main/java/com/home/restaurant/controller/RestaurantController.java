package com.home.restaurant.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping
	public Page<RestaurantDTO> getRestaurants(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<RestaurantDTO> result = restaurantService.getRestaurants(pageRequest);
		return result;
	}
}
