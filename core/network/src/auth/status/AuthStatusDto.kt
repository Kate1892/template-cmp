package com.eps.pakistan.network.auth.status

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AuthStatusDto(
    @SerialName("active")
    val active: Boolean,
    @SerialName("untied")
    val untied: Boolean,
    @SerialName("password_session_id")
    val passwordSessionId: String?,
    @SerialName("need_to_register")
    val needToRegister: Boolean,
)
