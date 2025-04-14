//
//  HomeView.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-12.
//

import Foundation
import SwiftUI

struct HomeView: View {
    
    @StateObject var homeViewModel: HomeViewModel
    
    init(appContext: AppContext) {
        _homeViewModel = StateObject(wrappedValue: HomeViewModel(restaurantService: appContext.services.restaurantService))
    }

    var body: some View {
        VStack {
            Text("Hello, World! \(homeViewModel.restaurants.count)")
        }.onAppear() {
            homeViewModel.retrieveRestaurants()
        }
    }
}
