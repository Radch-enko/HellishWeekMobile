//
//  PreviewScreen.swift
//  HellishWeekIOS
//
//  Created by Stanislav Radchenko on 17.11.2022.
//

import SwiftUI

struct PreviewScreen: View {
    
    var body: some View {
        VStack {
            Text("Sample Preview screen text")
        }
        .background(.red)
        .opacity(0.2)
    }
}

struct PreviewScreen_Previews: PreviewProvider {
    static var previews: some View {
        PreviewScreen()
    }
}
