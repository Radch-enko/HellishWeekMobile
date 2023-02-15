package com.blesscompany.hellishweek.features.home

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform