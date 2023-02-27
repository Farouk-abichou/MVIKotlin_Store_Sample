package com.example.mvi_application

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform