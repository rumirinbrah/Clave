import SwiftUI

@main
struct iOSApp: App {
    init(){
        application(IOSApplicationComponent())
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}