package com.template.cmp.core.network.auth

import com.template.cmp.core.network.common.ApiRoute
import com.template.cmp.core.network.common.api.ApiHttpClient
import com.template.cmp.core.network.common.api.request.GetRequest
import com.template.cmp.core.network.common.api.request.PostRequest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatformTools

class AuthApi(
    private val apiHttpClient: ApiHttpClient,
) {
    private val get by KoinPlatformTools.defaultContext().get().inject<GetRequest>()
    private val post by KoinPlatformTools.defaultContext().get().inject<PostRequest>()
    internal val jsonWithDefaults = Json { encodeDefaults = true }

    suspend fun mockLogin(){
        val result = get.getRequest<Unit>(
            url = ApiRoute.AUTH,
        )
    }
}

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

// Define your response model (if applicable)
@Serializable
data class LoginResponse(
    val id: Int,
    val title: String,
    val body: String
)