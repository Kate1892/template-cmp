package com.eps.pakistan.network.common.api.response

import com.eps.pakistan.network.common.api.request.ResultInterceptor
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

internal suspend fun Exception.toApiResponseError(): ApiResponse.Error<out ResponseErrorBody> {
    return when (this) {
        is ClientRequestException -> {
            ResultInterceptor.handleError(
                ApiResponse.Error.HttpError(
                    this.response.status.value,
                    this.getErrorBody()
                )
            )
        }

        is ServerResponseException -> {
            ResultInterceptor.handleError(
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
