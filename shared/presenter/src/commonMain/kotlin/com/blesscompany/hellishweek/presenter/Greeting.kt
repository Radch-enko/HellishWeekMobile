package com.blesscompany.hellishweek.presenter

import com.blesscompany.hellishweek.resources.Resources
import dev.icerock.moko.resources.ImageResource

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Hello, ${platform.name}!"
    }

    fun getImage(): ImageResource {
        return Resources.images.component
    }
}