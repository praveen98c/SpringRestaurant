//
//  HomeViewModel.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-12.
//

import Foundation

class HomeViewModel: ObservableObject {
    
    @Published var restaurants: [RestaurantDTO] = []
    
    let restaurantService: RestaurantServiceProtocol
    
    init(restaurantService: RestaurantServiceProtocol) {
        self.restaurantService = restaurantService
    }
    
    func retrieveRestaurants() {
        Task {
            let result = await restaurantService.getRestaurants()
            switch result {
            case .success(let restaurants):
                await updateRestaurants(restaurants: restaurants)
            case .failure(let error):
                print("Error: \(error)")
            }
        }
    }
    
    @MainActor
    func updateRestaurants(restaurants: [RestaurantDTO]) {
        self.restaurants = restaurants
    }
}
