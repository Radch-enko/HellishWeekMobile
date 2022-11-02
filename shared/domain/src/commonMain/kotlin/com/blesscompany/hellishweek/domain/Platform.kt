package com.blesscompany.hellishweek.domain

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform