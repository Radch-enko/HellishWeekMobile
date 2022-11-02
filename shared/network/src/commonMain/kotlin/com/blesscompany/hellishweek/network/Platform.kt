package com.blesscompany.hellishweek.network

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform