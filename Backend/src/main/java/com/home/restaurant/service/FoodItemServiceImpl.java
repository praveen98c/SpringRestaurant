package com.home.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.home.restaurant.dto.model.FoodItemDTO;
import com.home.restaurant.dto.model.MenuDTO;
import com.home.restaurant.model.FoodItem;
import com.home.restaurant.model.Menu;
import com.home.restaurant.repository.FoodItemRepository;
import com.home.restaurant.service.interfaces.FoodItemService;

@Service
public class FoodItemServiceImpl implements FoodItemService {

	private FoodItemRepository foodItemRepository;
	
	FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
		this.foodItemRepository = foodItemRepository;
	}
	
	@Override
	public List<FoodItemDTO> getFoodItemsByMenuId(Long menuId) {
		List<FoodItem> menus = foodItemRepository.findByMenuId(menuId);
		return menus.stream().map(FoodItemDTO::new).collect(Collectors.toList());
	}
}
