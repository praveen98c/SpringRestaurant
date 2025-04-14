//
//  HomeScreen.swift
//  SpringRestaurantIOSApp
//
//  Created by Praveen on 2025-04-02.
//

import Foundation
import SwiftUI

struct HomeScreen: View {
    
    var authManaging: AuthManagingProtocol
    let appContext: AppContext
    
    init(appContext: AppContext, authManaging: AuthManagingProtocol) {
        self.authManaging = authManaging
        self.appContext = appContext
    }
    
    var body: some View {
        HomeView(appContext: appContext)
        Button("Logout", action: {
            Task {
                authManaging.logout()
            }
        })
    }
}
