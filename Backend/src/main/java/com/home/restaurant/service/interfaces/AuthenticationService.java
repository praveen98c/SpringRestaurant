package com.home.restaurant.service.interfaces;

import com.home.restaurant.model.User;

public interface AuthenticationService {
	User signIn(User signInRequest);
}
