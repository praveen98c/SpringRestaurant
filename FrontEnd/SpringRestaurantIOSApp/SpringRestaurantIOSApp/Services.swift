//
//  Services.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-14.
//

import Foundation

protocol ServicesProtocol {
    var restaurantService: RestaurantService { get }
}

struct Services: ServicesProtocol {
    
    let restaurantService: RestaurantService
    
    init(restApiClient: RestApiProtocol) {
        self.restaurantService = RestaurantService(restApiClient: restApiClient)
    }
}
