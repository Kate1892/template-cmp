package com.template.cmp.core.network.common.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
sealed class ResponseSuccessBody<T> {

    abstract val success: Int
    abstract val data: T
    abstract val timestamp: Long


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

@Serializable
class ResponseErrorBody(
    @SerialName("success")
    val success: Int,
    @SerialName("error")
    val error: ResponseError,
    @SerialName("timestamp")
    val timestamp: Long,
)

@Serializable(with = ResponseErrorSerializer::class)
sealed class ResponseError {
    abstract val code: Int
    abstract val message: String
    abstract val owner: String?

    @Serializable
    class ErrorWithStringData(
        @SerialName("code")
        override val code: Int,
        @SerialName("message")
        override val message: String,
        @SerialName("owner")
        override val owner: String?,
        @SerialName("data")
        val data: String?,
    ) : ResponseError()
}

object ResponseErrorSerializer : JsonContentPolymorphicSerializer<ResponseError>(ResponseError::class) {
    override fun selectDeserializer(element: JsonElement) = when {
        "data" in element.jsonObject &&
                element.jsonObject["data"] is JsonObject -> ResponseError.ErrorWithStringData.serializer()
        else -> ResponseError.ErrorWithStringData.serializer()
    }
}