package com.eps.pakistan.network.auth.checkpassword

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class CheckPasswordRequestBody(
    @SerialName("device_token")
    val deviceToken: String,
    @SerialName("password_old")
    val passwordOld: String,
) : PostRequestBody
