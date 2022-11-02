package com.blesscompany.hellishweek.presenter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform