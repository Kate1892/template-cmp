package com.template.cmp.core.network.common.api.response

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.serialization.JsonConvertException
import io.ktor.utils.io.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.SerializationException

internal suspend fun Exception.toApiResponseError(
    handleError: (ApiResponse.Error.HttpError<ResponseErrorBody>) -> ApiResponse.Error<out ResponseErrorBody>,
): ApiResponse.Error<out ResponseErrorBody> {
    return when (this) {
        is ClientRequestException -> {
            handleError(
                ApiResponse.Error.HttpError(
                    this.response.status.value,
                    this.getErrorBody()
                )
            )
        }

        is ServerResponseException -> {
            handleError(
                ApiResponse.Error.HttpError(
                    this.response.status.value,
                    this.getErrorBody()
                )
            )
        }

        is SerializationException,
        is JsonConvertException -> {
            ApiResponse.Error.SerializationError
        }

        is ConnectTimeoutException,
        is CancellationException,
        is TimeoutCancellationException,
        is HttpRequestTimeoutException -> {
            ApiResponse.Error.TimeoutError
        }

        else -> {
            ApiResponse.Error.NetworkError
        }
    }
}

internal suspend fun ResponseException.getErrorBody(): ResponseErrorBody? =
    try {
        response.body<ResponseErrorBody>()
    } catch (e: NoTransformationFoundException) {
        null
    } catch (e: Exception) {
        null
    }
