package com.blesscompany.hellishweek.models

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform