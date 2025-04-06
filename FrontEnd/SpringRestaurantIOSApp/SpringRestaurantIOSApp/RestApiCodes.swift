//
//  RestApiCodes.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-05.
//

import Foundation

enum RestApiSuccessCodes: Int {
    
    case registerSuccess = 1001
    case loginSuccess = 1002
    case foodItemsByMenuRetrieveSuccess = 1100
    case menuItemsByRestaurantRetrieveSuccess = 1200
    case restaurantRetrieveSuccess = 1300
    
    var localizedMessage: String {
        switch self {
        case .registerSuccess:
            return NSLocalizedString("User registered successfully", comment: "Registration success message")
        case .loginSuccess:
            return NSLocalizedString("Login successful", comment: "Login success message")
        case .foodItemsByMenuRetrieveSuccess:
            return NSLocalizedString("Successfully retrieved food items of menu", comment: "Food items retrieval success message")
        case .menuItemsByRestaurantRetrieveSuccess:
            return NSLocalizedString("Successfully retrieved menus of restaurant", comment: "Restaurant menu retrieval success message")
        case .restaurantRetrieveSuccess:
            return NSLocalizedString("Successfully retrieved restaurant", comment: "Restaurant retrieval success message")
        }
    }
}

enum RestApiErrorCodes: Int {
    
    case userAlreadyExists = 2001
    case userNotFound = 2002
    case authFailed = 2003
    
    var localizedMessage: String {
        switch self {
        case .userAlreadyExists:
            return NSLocalizedString("User already exists", comment: "User already exists error")
        case .userNotFound:
            return NSLocalizedString("User not found", comment: "User not found error")
        case .authFailed:
            return NSLocalizedString("Authentication failed", comment: "Authentication failure error")
        }
    }
}
