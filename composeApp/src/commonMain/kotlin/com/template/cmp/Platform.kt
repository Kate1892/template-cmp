package com.template.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform