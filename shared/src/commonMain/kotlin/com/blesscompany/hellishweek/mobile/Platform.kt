package com.blesscompany.hellishweek.mobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform