package com.eps.pakistan.network.common.api

import com.eps.pakistan.network.NetworkData
import com.eps.pakistan.network.common.HEADER_TITLE_ACCEPT_LANGUAGE
import com.eps.pakistan.network.common.HTTP_TIMEOUT_MS
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.EMPTY
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val DEV_URL = "api-nodejs-todolist.herokuapp.com"
private const val PROD_URL = "api-nodejs-todolist.herokuapp.com"

internal object ApiHttpClient {

    private val baseUrl get() = if (NetworkData.isProduction) PROD_URL else DEV_URL

    internal val client = HttpClient {
        install(Logging) {
            logger = if (NetworkData.isLoggingEnabled) Logger.SIMPLE else Logger.EMPTY
            level = if (NetworkData.isLoggingEnabled) LogLevel.BODY else LogLevel.NONE
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = HTTP_TIMEOUT_MS
            connectTimeoutMillis = HTTP_TIMEOUT_MS
            socketTimeoutMillis = HTTP_TIMEOUT_MS
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = baseUrl
            }
            header(HEADER_TITLE_ACCEPT_LANGUAGE, NetworkData.deviceLanguage)
        }
    }
}
