package com.blesscompany.hellishweek.resources

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform