package com.eps.pakistan.network.auth.repeatotp

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class RepeatOtpRequestBody(
    @SerialName("session_id")
    val sessionId: String,
) : PostRequestBody
