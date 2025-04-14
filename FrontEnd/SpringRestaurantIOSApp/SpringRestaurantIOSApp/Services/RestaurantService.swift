//
//  RestaurantService.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-14.
//

import Foundation

protocol RestaurantServiceProtocol {
    func getRestaurants() async -> Result<[RestaurantDTO], RestAPIError>
}

class RestaurantService: RestaurantServiceProtocol {
    
    private let restApiClient: RestApiProtocol
    
    init(restApiClient: RestApiProtocol) {
        self.restApiClient = restApiClient
    }
    
    func getRestaurants() async -> Result<[RestaurantDTO], RestAPIError> {
        let result = await restApiClient.getRestaurantById(id: 10)
        switch result {
        case .success(let restaurants):
            return .success([restaurants])
        case .failure(let error):
            return .failure(error)
        }
    }
}
