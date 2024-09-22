package com.eps.pakistan.network.common.api.response

import com.eps.pakistan.network.common.api.response.ApiResponse.Error.HttpError

internal typealias EpsApiResponse<T> = ApiResponse<ResponseSuccessBody.Signed<T>, ResponseErrorBody>
internal typealias EpsApiResponseNotSigned<T> = ApiResponse<ResponseSuccessBody.NotSigned<T>, ResponseErrorBody>

sealed class ApiResponse<out T, out E>(val isSuccessful: Boolean) {

    class Success<T>(val body: T) : ApiResponse<T, Nothing>(true)

    sealed class Error<E> : ApiResponse<Nothing, E>(false) {

        data class HttpError<E>(
            val code: Int,
            val errorBody: E?,
            val isHandled: Boolean = false
        ) : Error<E>()

        data object NetworkError : Error<Nothing>()

        data object SerializationError : Error<Nothing>()

        data object ServerSignError : Error<Nothing>()

        data object TimeoutError : Error<Nothing>()
    }
}

fun <E> HttpError<E>.handledByInterceptor(): HttpError<E> {
    return this.copy(isHandled = true)
}
