package com.example.complexhomepage

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform