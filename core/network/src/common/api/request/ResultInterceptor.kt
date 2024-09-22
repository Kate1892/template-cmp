
import com.eps.pakistan.network.common.api.response.ApiResponse
import com.eps.pakistan.network.common.api.response.ResponseErrorBody
import com.eps.pakistan.network.common.api.response.handledByInterceptor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class InterceptorErrorAction {
    data object NoError : InterceptorErrorAction()
    class LogoutWithError(val errorText: String) : InterceptorErrorAction()
    class AccountBlocked(val message: String) : InterceptorErrorAction()
}

object ResultInterceptor {

    const val SERVER_CODE_400 = 400

    private val _errorAction = MutableStateFlow<InterceptorErrorAction>(InterceptorErrorAction.NoError)
    val errorAction = _errorAction.asStateFlow()

    fun onErrorActionExecuted() {
        _errorAction.update { InterceptorErrorAction.NoError }
    }

    @Suppress("MagicNumber")
    fun handleError(
        error: ApiResponse.Error.HttpError<ResponseErrorBody>
    ): ApiResponse.Error.HttpError<ResponseErrorBody> {
        val httpCode = error.code
        val serverCode = error.errorBody?.error?.code ?: 200
        val errorMessage = error.errorBody?.error?.message ?: "Error"

        return when (httpCode) {
            401 -> {
                _errorAction.update { InterceptorErrorAction.LogoutWithError(errorText = errorMessage) }
                error.handledByInterceptor()
            }

            else -> {
                when (serverCode) {
                    40315 -> {
                        // TODO For test, remove later
                        _errorAction.update { InterceptorErrorAction.AccountBlocked(message = errorMessage) }
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
