//
//  ApiResponse.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-05.
//

import Foundation

struct ApiResponse<T: Codable>: Codable {
    let message: String
    let code: Int
    let data: T?
}

struct EmptyResponse: Codable {}
