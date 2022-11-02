package com.blesscompany.hellishweek.common

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform