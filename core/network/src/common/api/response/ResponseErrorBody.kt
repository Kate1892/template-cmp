package com.eps.pakistan.network.common.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

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

    @Serializable
    class ErrorWithObjectData(
        @SerialName("code")
        override val code: Int,
        @SerialName("message")
        override val message: String,
        @SerialName("owner")
        override val owner: String?,
        @SerialName("data")
        val data: JsonObject?,
    ) : ResponseError()
}

object ResponseErrorSerializer : JsonContentPolymorphicSerializer<ResponseError>(ResponseError::class) {
    override fun selectDeserializer(element: JsonElement) = when {
        "data" in element.jsonObject &&
            element.jsonObject["data"] is JsonObject -> ResponseError.ErrorWithObjectData.serializer()
        else -> ResponseError.ErrorWithStringData.serializer()
    }
}
