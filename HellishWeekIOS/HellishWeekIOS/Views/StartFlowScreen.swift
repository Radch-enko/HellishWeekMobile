//
//  SwiftUIView.swift
//  HellishWeekIOS
//
//  Created by Stanislav Radchenko on 17.11.2022.
//

import SwiftUI

struct StartFlowScreen: View {
    var body: some View {
        ZStack(alignment: .center) {
            Image("background_wave").resizable()
            SplashScreen()
        }.edgesIgnoringSafeArea([.top, .bottom])
        
    }
}

struct SwiftUIView_Previews: PreviewProvider {
    static var previews: some View {
        StartFlowScreen()
    }
}
