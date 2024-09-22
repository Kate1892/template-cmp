package com.eps.pakistan.network.auth.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LoginDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("account_token")
    val accountToken: String,
    @SerialName("keystore")
    val keystore: String,
    @SerialName("need_to_register")
    val needToRegister: Boolean,
)
