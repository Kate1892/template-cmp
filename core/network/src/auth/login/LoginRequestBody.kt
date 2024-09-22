package com.eps.pakistan.network.auth.login

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class LoginRequestBody(
    @SerialName("device_token")
    val deviceToken: String,
    @SerialName("password")
    val password: String,
) : PostRequestBody
