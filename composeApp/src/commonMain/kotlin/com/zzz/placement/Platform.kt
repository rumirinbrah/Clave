package com.zzz.placement

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform