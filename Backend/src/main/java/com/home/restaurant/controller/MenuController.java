package com.home.restaurant.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.restaurant.constants.RestApiPaths;
import com.home.restaurant.dto.model.MenuDTO;
import com.home.restaurant.dto.response.ApiResponse;
import com.home.restaurant.service.interfaces.MenuService;

@RestController
@RequestMapping(RestApiPaths.Menus.BASE)
public class MenuController {

	private MenuService menuService;

	MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping(RestApiPaths.Menus.RESTAURANT_BY_ID)
	public ResponseEntity<ApiResponse<List<MenuDTO>>> getMenus(@PathVariable(RestApiPaths.PathVariables.ID) Long id) {
		List<MenuDTO> menus = menuService.getMenusByRestaurantId(id);
		ApiResponse<List<MenuDTO>> response = new ApiResponse<>(menus, "Successfully retrieved menus of restaurant");
		return menus != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
	}
}