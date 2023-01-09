package com.blesscompany.hellishweek.navigation

import com.blesscompany.hellishweek.shared.navigation.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()