//
//  RestApiClient.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-02.
//

import Foundation

protocol RestApiProtocol {
    func login(username: String, password: String) async -> Result<ApiResponse<LoginData>, RestAPIError>
    func register(username: String, password: String, name: String) async -> Result<ApiResponse<EmptyResponse>, RestAPIError>
    func getRestaurantById(id: Int64) async -> Result<ApiResponse<RestaurantData>, RestAPIError>
}

enum RestEndPoint: String {
    case login = "/api/auth/login"
    case register = "/api/auth/register"
    case restaurants = "/api/restaurants/{id}"
}

final class RestApiClient: RestApiProtocol {
    
    let networkManager: NetworkManagerProtocol
    let baseUrl: String
    var keyChainValues: KeyChainValuesProtocol
    
    init(networkManager: NetworkManagerProtocol, baseUrl: String, keyChainValues: KeyChainValuesProtocol) {
        self.networkManager = networkManager
        self.baseUrl = baseUrl
        self.keyChainValues = keyChainValues
    }
    
    func login(username: String, password: String) async -> Result<ApiResponse<LoginData>, RestAPIError> {
        let loginRequest = LoginRequest(baseURL: baseUrl, username: username, password: password)
        let response = await networkManager.request(request: loginRequest)
        let loginResponse: Result<ApiResponse<LoginData>, RestAPIError> = parseResult(response)
        switch loginResponse {
        case .success(let loginResponseObject):
            keyChainValues.authToken = loginResponseObject.data?.token
        case .failure(_):
            break
        }
        return loginResponse
    }
    
    func register(username: String, password: String, name: String) async -> Result<ApiResponse<EmptyResponse>, RestAPIError> {
        let registerRequest = RegisterRequest(baseURL: baseUrl, username: username, password: password, name: name)
        let response = await networkManager.request(request: registerRequest)
        return parseResult(response)
    }
    
    func getRestaurantById(id: Int64) async -> Result<ApiResponse<RestaurantData>, RestAPIError> {
        guard let token = keyChainValues.authToken else { fatalError() }
        let restaurantRequest = RestaurantRequest(baseURL: baseUrl, headers: authHeader(token: token), restaurantId: id)
        let response = await networkManager.request(request: restaurantRequest)
        return parseResult(response)
    }
}

fileprivate extension RestApiClient {
    
    func parseResult<T: Decodable>(_ result: Result<Data, NetworkErrorData>) -> Result<T, RestAPIError>  {
        do {
            switch result {
            case .success(let data):
                let decoded =  try JSONDecoder().decode(T.self, from: data)
                return .success(decoded)
            case .failure(let networkError):
                var apiResponse: ApiResponse<EmptyResponse>? = nil
                if let data = networkError.data {
                    apiResponse = try JSONDecoder().decode(ApiResponse<EmptyResponse>.self, from: data)
                }
                
                let message = apiResponse?.message ?? "Unknown error"
                let code = apiResponse?.code ?? -999
                throw RestAPIError.apiError(message, code)
            }
        } catch let error as RestAPIError {
            return .failure(error)
        } catch {
            return .failure(.parseError(error))
        }
    }
    
    func authHeader(token: String) -> [String: String] {
        return ["Authorization": "Bearer \(token)"]
    }
}

enum RestAPIError: Error {
    case apiError(String, Int)
    case parseError(Error)
}
