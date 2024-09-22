package com.eps.pakistan.network.auth.confirmotp

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ConfirmOtpRequestBody(
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("code")
    val code: String,
) : PostRequestBody
