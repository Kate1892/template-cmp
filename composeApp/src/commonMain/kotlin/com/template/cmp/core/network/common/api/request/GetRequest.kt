package com.template.cmp.core.network.common.api.request

import com.template.cmp.core.network.common.api.ApiHttpClient
import com.template.cmp.core.network.common.api.response.ApiResponse
import com.template.cmp.core.network.common.api.response.NoteApiResponse
import com.template.cmp.core.network.common.api.response.toApiResponseError
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.isSuccess


internal class GetRequest(
    private val apiHttpClient: ApiHttpClient,
    private val resultInterceptor: Interceptor,
) {
    internal suspend inline fun <reified T> getRequest(
        url: String,
        queryParams: Map<String, String> = mapOf(),
    ): NoteApiResponse<T> {
        return with(apiHttpClient.client) {
            try {
                val response = get {
                    url(url)
                    queryParams.forEach { queryParam ->
                        parameter(queryParam.key, queryParam.value)
                    }
                }
                if (response.status.isSuccess()) {
                    ApiResponse.Success(response.body())
                } else {
                    resultInterceptor.handleError(
                        ApiResponse.Error.HttpError(
                            response.status.value,
                            response.body()
                        )
                    )
                }
            } catch (e: Exception) {
                e.toApiResponseError(resultInterceptor::handleError)
            }
        }
    }
}