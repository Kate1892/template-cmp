package com.eps.pakistan.network.common.api.request

import com.eps.pakistan.network.common.HEADER_PREFIX_AUTHORIZATION
import com.eps.pakistan.network.common.HEADER_TITLE_AUTHORIZATION
import com.eps.pakistan.network.common.MULTIPART_JSON_DATA_CONTENT_TITLE
import com.eps.pakistan.network.common.MULTIPART_PHOTO_CONTENT_DISPOSITION
import com.eps.pakistan.network.common.MULTIPART_PHOTO_CONTENT_TITLE
import com.eps.pakistan.network.common.api.response.ApiResponse
import com.eps.pakistan.network.common.api.response.EpsApiResponse
import com.eps.pakistan.network.common.api.response.toApiResponseError
import com.eps.pakistan.network.common.api.secure.checkServerSignAuth
import com.eps.pakistan.network.common.api.secure.checkServerSignNotAuth
import com.eps.pakistan.network.common.storage.NetworkStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI

@OptIn(InternalAPI::class)
internal suspend inline fun <reified T> HttpClient.postRequestNotAuth(
    url: String,
    requestBody: String,
    addAuthHeader: Boolean = false,
    checkServerSign: Boolean = true,
): EpsApiResponse<T> {
    return try {
        val response = post {
            url(url)
            if (addAuthHeader) {
                header(
                    HEADER_TITLE_AUTHORIZATION,
                    "$HEADER_PREFIX_AUTHORIZATION ${NetworkStorage.accessToken}"
                )
            }
            body = TextContent(
                text = requestBody,
                contentType = ContentType.Application.Json,
            )
        }
        if (response.status.isSuccess()) {
            if (checkServerSign && !checkServerSignNotAuth(response.bodyAsText())) {
                ApiResponse.Error.ServerSignError
            } else {
                ApiResponse.Success(response.body())
            }
        } else {
            ResultInterceptor.handleError(ApiResponse.Error.HttpError(response.status.value, response.body()))
        }
    } catch (e: Exception) {
        e.toApiResponseError()
    }
}

@OptIn(InternalAPI::class)
internal suspend inline fun <reified T> HttpClient.postRequestAuth(
    url: String,
    requestBody: String,
    checkServerSign: Boolean = true,
): EpsApiResponse<T> {
    return try {
        val response = post {
            url(url)
            header(
                HEADER_TITLE_AUTHORIZATION,
                "$HEADER_PREFIX_AUTHORIZATION ${NetworkStorage.accessToken}"
            )
            body = TextContent(
                text = requestBody,
                contentType = ContentType.Application.Json,
            )
        }
        if (response.status.isSuccess()) {
            if (checkServerSign && !checkServerSignAuth(response.bodyAsText())) {
                ApiResponse.Error.ServerSignError
            } else {
                ApiResponse.Success(response.body())
            }
        } else {
            ResultInterceptor.handleError(ApiResponse.Error.HttpError(response.status.value, response.body()))
        }
    } catch (e: Exception) {
        e.toApiResponseError()
    }
}

internal suspend inline fun <reified T> HttpClient.postRequestAuthMultipartPhoto(
    url: String,
    requestBody: String,
    data: ByteArray,
    checkServerSign: Boolean = true,
): EpsApiResponse<T> {
    return try {
        val response = post {
            url(url)
            header(
                HEADER_TITLE_AUTHORIZATION,
                "$HEADER_PREFIX_AUTHORIZATION ${NetworkStorage.accessToken}"
            )
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append(MULTIPART_JSON_DATA_CONTENT_TITLE, requestBody)
                        append(
                            MULTIPART_PHOTO_CONTENT_TITLE,
                            data,
                            Headers.build {
                                append(HttpHeaders.ContentDisposition, MULTIPART_PHOTO_CONTENT_DISPOSITION)
                            }
                        )
                    }
                )
            )
        }
        if (response.status.isSuccess()) {
            if (checkServerSign && !checkServerSignAuth(response.bodyAsText())) {
                ApiResponse.Error.ServerSignError
            } else {
                ApiResponse.Success(response.body())
            }
        } else {
            ResultInterceptor.handleError(ApiResponse.Error.HttpError(response.status.value, response.body()))
        }
    } catch (e: Exception) {
        e.toApiResponseError()
    }
}

internal interface PostRequestBody
