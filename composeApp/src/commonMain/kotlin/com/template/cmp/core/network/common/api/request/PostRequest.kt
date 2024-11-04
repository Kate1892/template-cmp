package com.template.cmp.core.network.common.api.request

import com.template.cmp.core.network.common.api.ApiHttpClient
import com.template.cmp.core.network.common.api.response.ApiResponse
import com.template.cmp.core.network.common.api.response.NoteApiResponse
import com.template.cmp.core.network.common.api.response.toApiResponseError
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI

class PostRequest(
    private val apiHttpClient: ApiHttpClient,
    private val resultInterceptor: Interceptor

) {
    @OptIn(InternalAPI::class)
    internal suspend inline fun <reified T> postRequest(
        url: String,
        requestBody: String,
    ){
//    : NoteApiResponse<T> {
        return with(apiHttpClient.client) {
            try {
                val response = post {
                    url(url)
                    body = TextContent(
                        text = requestBody,
                        contentType = ContentType.Application.Json,
                    )
                }
                if (response.status.isSuccess()) {
//                    ApiResponse.Success(response.body())
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