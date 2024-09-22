package com.eps.pakistan.network.auth.status

import com.eps.pakistan.network.common.api.request.PostRequestBody
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class AuthStatusRequestBody(
    @SerialName("device_token")
    val deviceToken: String,
) : PostRequestBody
