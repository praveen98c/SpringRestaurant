package com.home.restaurant.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.restaurant.constants.RestApiPaths;
import com.home.restaurant.dto.model.FoodItemDTO;
import com.home.restaurant.dto.response.ApiResponse;
import com.home.restaurant.service.interfaces.FoodItemService;

@RestController
@RequestMapping(RestApiPaths.FoodItems.BASE)
public class FoodItemController {

	private FoodItemService foodItemService;

	FoodItemController(FoodItemService foodItemService) {
		this.foodItemService = foodItemService;
	}

	@GetMapping(RestApiPaths.FoodItems.MENU_BY_ID)
	public ResponseEntity<ApiResponse<List<FoodItemDTO>>> getFoodItemsByMenuId(
			@PathVariable(RestApiPaths.PathVariables.ID) Long id) {
		List<FoodItemDTO> foodItems = foodItemService.getFoodItemsByMenuId(id);
		ApiResponse<List<FoodItemDTO>> response = new ApiResponse<>(foodItems,
				"Successfully retrieved food items of menu");
		return foodItems != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}
}
