package com.eps.pakistan.network.auth.confirmotp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ConfirmOtpDto(
    @SerialName("action")
    val action: ConfirmOtpActionDto,
    @SerialName("device_token")
    val deviceToken: String,
    @SerialName("public_key")
    val publicKey: String,
    @SerialName("password_session_id")
    val passwordSessionId: String? = null,
    @SerialName("need_to_register")
    val needToRegister: Int? = null,
)

@Serializable
enum class ConfirmOtpActionDto {
    @SerialName("login")
    Login,

    @SerialName("create_password")
    CreatePassword,
}
