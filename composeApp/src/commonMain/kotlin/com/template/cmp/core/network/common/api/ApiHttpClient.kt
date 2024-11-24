package com.template.cmp.core.network.common.api

import com.template.cmp.core.network.common.HEADER_TITLE_ACCEPT_LANGUAGE
import com.template.cmp.core.network.common.HTTP_TIMEOUT_MS
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

/** for testing ktor*/
private const val BASE_URL = "jsonplaceholder.typicode.com"

class ApiHttpClient {
    private var isLoggingEnabled: Boolean = false
    private val deviceLanguage: String = "en"

    private val baseUrl get() = BASE_URL
    private val logger get() = if (isLoggingEnabled) Logger.SIMPLE else Logger.EMPTY
    private val loggerLevel get() = if (isLoggingEnabled) LogLevel.BODY else LogLevel.NONE


    fun initHttpClient(
        isProduction: Boolean,
        isLoggingEnabled: Boolean,
    ) {
        this.isLoggingEnabled = isLoggingEnabled
    }

    internal val client by lazy {
        HttpClient {
            install(Logging) {
                logger = this@ApiHttpClient.logger
                level = this@ApiHttpClient.loggerLevel
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
                header(HEADER_TITLE_ACCEPT_LANGUAGE, deviceLanguage)
            }
        }
    }
}