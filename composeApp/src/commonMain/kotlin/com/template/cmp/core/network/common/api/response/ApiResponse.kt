package com.template.cmp.core.network.common.api.response

internal typealias NoteApiResponse<T>
        = ApiResponse<ResponseSuccessBody.NotSigned<T>, ResponseErrorBody>

sealed class ApiResponse<out T, out E>(val isSuccessful: Boolean) {
    class Success<T>(val body: T) : ApiResponse<T, Nothing>(true)
    sealed class Error<E> : ApiResponse<Nothing, E>(false) {

        data class HttpError<E>(
            val code: Int,
            val errorBody: E?,
        ) : Error<E>()

        data object NetworkError : Error<Nothing>()

        data object SerializationError : Error<Nothing>()

        data object ServerSignError : Error<Nothing>()

        data object TimeoutError : Error<Nothing>()
    }
}
fun <E> ApiResponse.Error.HttpError<E>.handledByInterceptor(): ApiResponse.Error.HttpError<E> {
    return this.copy()
}
