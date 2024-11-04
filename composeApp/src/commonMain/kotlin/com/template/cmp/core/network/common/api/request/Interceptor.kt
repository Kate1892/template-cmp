package com.template.cmp.core.network.common.api.request

import com.template.cmp.core.network.common.api.response.ApiResponse
import com.template.cmp.core.network.common.api.response.ResponseErrorBody
import com.template.cmp.core.network.common.api.response.handledByInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class InterceptorErrorAction {
    data object NoError : InterceptorErrorAction()
    class TerminalError(val errorText: String) : InterceptorErrorAction()
}

class Interceptor {
    private val _interceptorError =
        MutableStateFlow<InterceptorErrorAction>(InterceptorErrorAction.NoError)
    val interceptorError = _interceptorError.asStateFlow()

    fun onErrorActionExecuted() {
        _interceptorError.update { InterceptorErrorAction.NoError }
    }

    @Suppress("MagicNumber")
    fun handleError(
        error: ApiResponse.Error.HttpError<ResponseErrorBody>
    ): ApiResponse.Error.HttpError<ResponseErrorBody> {
        val httpCode = error.code
        val serverCode = error.errorBody?.error?.code ?: 200
        val errorMessage = error.errorBody?.error?.message ?: ""

        return when (httpCode) {
            401 -> {
                _interceptorError.update { InterceptorErrorAction.TerminalError(errorText = errorMessage) }
                error.handledByInterceptor()
            }

            else -> {
                when (serverCode) {
                    40315 -> {
                        // TODO For test, remove later
                        _interceptorError.update { InterceptorErrorAction.TerminalError(errorText = errorMessage) }
                        error.handledByInterceptor()
                    }

                    else -> {
                        error
                    }
                }
            }
        }
    }
}
