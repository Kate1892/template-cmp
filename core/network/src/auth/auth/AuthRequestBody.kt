package com.eps.pakistan.network.auth.auth

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AuthRequestBody(
    @SerialName("phone")
    val phone: String,
    @SerialName("captcha_token")
    val captchaToken: String,
    @SerialName("device_code")
    val deviceCode: String,
    @SerialName("device_name")
    val deviceName: String,
    @SerialName("device_type")
    val deviceType: Int,
    @SerialName("public_key")
    val publicKey: String,
    @SerialName("fcm_token")
    val fcmToken: String,
    @SerialName("reset_password")
    val resetPassword: Boolean = false,
    @SerialName("action")
    val action: String = "login",
) : PostRequestBody
