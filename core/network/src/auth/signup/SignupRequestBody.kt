package com.eps.pakistan.network.auth.signup

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class SignupRequestBody(
    @SerialName("phone")
    val phone: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("email")
    val email: String,
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
    @SerialName("captcha_token")
    val captchaToken: String,
    @SerialName("birthdate")
    val birthdate: String = "1990-01-01", // TODO
    @SerialName("reset_password")
    val resetPassword: Boolean = false,
    @SerialName("action")
    val action: String = "register",
) : PostRequestBody
