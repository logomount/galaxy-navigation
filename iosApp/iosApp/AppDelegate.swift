//
//  AppDelegate.swift
//  iosApp
//
//  Created by Fedor Erofeev on 19/9/2568 BE.
//

import SwiftUI
import ComposeApp

@MainActor
class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        let path = url.absoluteString
        ExternalUriHandler.shared.onNewUri(uri: path)
        return true
    }
}
