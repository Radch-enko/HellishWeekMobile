package com.blesscompany.hellishweek.shared.navigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform