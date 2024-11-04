package com.template.cmp.core.network.common.remoteresult

sealed class RemoteResult<out T> {

    class Success<T>(val data: T) : RemoteResult<T>()

    sealed class Error : RemoteResult<Nothing>() {

        class ServerError(val error: RemoteError) : Error()

        data object NetworkError : Error()

        data object SerializationError : Error()

        data object ServerSignError : Error()

        data object TimeoutError : Error()

        fun isErrorHandled() = this is ServerError && this.error.isHandled
    }
}

class RemoteError(
    val httpCode: Int,
    val serverCode: Int,
    val message: String,
    val owner: String,
    val isHandled: Boolean = false,
)
