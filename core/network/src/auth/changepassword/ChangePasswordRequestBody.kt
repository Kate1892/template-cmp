package com.eps.pakistan.network.auth.changepassword

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ChangePasswordRequestBody(
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("password")
    val password: String,
    @SerialName("password_repeat")
    val passwordRepeat: String,
) : PostRequestBody
