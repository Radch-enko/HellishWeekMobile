package com.blesscompany.hellishweek.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform