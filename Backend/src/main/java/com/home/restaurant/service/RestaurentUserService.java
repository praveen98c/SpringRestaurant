
package com.home.restaurant.service;

import com.home.restaurant.exception.UserAlreadyExistsException;
import com.home.restaurant.model.User;
import com.home.restaurant.repository.UserRepository;
import com.home.restaurant.service.interfaces.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RestaurentUserService implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public RestaurentUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerUser(String name, String username, String password) {
		if (userRepository.findByUsername(username).isPresent()) {
			throw new UserAlreadyExistsException("Username already exists!");
		}

		User newUser = new User();
		newUser.setName(name);
		newUser.setUsername(username);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setRole("ROLE_USER");
		newUser.setCreateTime(LocalDateTime.now());
		userRepository.save(newUser);
	}
}