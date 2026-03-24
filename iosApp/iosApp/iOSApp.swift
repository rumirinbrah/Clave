import SwiftUI

@main
struct iOSApp: App {
    init(){
        application(IOSApplicationComponent())
        initKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}