package com.eps.pakistan.network.common.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ResponseSuccessBody<T> {

    abstract val success: Int
    abstract val data: T
    abstract val timestamp: Long

    @Serializable
    class Signed<T>(
        @SerialName("success")
        override val success: Int,
        @SerialName("response")
        override val data: T,
        @SerialName("timestamp")
        override val timestamp: Long,
        @SerialName("sign")
        val sign: String,
    ) : ResponseSuccessBody<T>()

    @Serializable
    class NotSigned<T>(
        @SerialName("success")
        override val success: Int,
        @SerialName("response")
        override val data: T,
        @SerialName("timestamp")
        override val timestamp: Long,
    ) : ResponseSuccessBody<T>()
}
