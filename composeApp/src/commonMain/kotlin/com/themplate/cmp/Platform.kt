package com.themplate.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform