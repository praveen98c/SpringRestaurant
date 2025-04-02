package com.home.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.home.restaurant.dto.model.MenuDTO;
import com.home.restaurant.model.Menu;
import com.home.restaurant.repository.MenuRepository;
import com.home.restaurant.service.interfaces.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	private MenuRepository menuRepository;

	public MenuServiceImpl(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public List<MenuDTO> getMenusByRestaurantId(Long restaurantId) {
		List<Menu> menus = menuRepository.findByRestaurantId(restaurantId);
		return menus.stream().map(MenuDTO::new).collect(Collectors.toList());
	}
}
