package com.blesscompany.hellishweek.injector

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform