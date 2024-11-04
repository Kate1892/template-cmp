package com.template.cmp.core.network.common.remoteresult

import com.template.cmp.core.network.common.api.response.ApiResponse
import com.template.cmp.core.network.common.api.response.ResponseErrorBody
import com.template.cmp.core.network.common.api.response.ResponseSuccessBody

internal fun <T, E> ApiResponse<ResponseSuccessBody<T>, ResponseErrorBody>.toRemoteResult(
    mapBlock: (T) -> E
): RemoteResult<E> {
    return when (this) {
        is ApiResponse.Success -> {
            RemoteResult.Success(mapBlock(this.body.data))
        }

        is ApiResponse.Error.HttpError -> {
            RemoteResult.Error.ServerError(
                error = RemoteError(
                    httpCode = this.code,
                    serverCode = this.errorBody?.error?.code ?: this.code,
                    message = this.errorBody?.error?.message.orEmpty(),
                    owner = this.errorBody?.error?.owner.orEmpty(),
                )
            )
        }

        ApiResponse.Error.NetworkError -> {
            RemoteResult.Error.NetworkError
        }

        ApiResponse.Error.SerializationError -> {
            RemoteResult.Error.SerializationError
        }

        ApiResponse.Error.ServerSignError -> {
            RemoteResult.Error.ServerSignError
        }

        ApiResponse.Error.TimeoutError -> {
            RemoteResult.Error.TimeoutError
        }
    }
}
