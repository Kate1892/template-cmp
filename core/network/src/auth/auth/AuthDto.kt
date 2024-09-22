package com.eps.pakistan.network.auth.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthDto(
    @SerialName("session_id")
    val sessionId: String,
)
