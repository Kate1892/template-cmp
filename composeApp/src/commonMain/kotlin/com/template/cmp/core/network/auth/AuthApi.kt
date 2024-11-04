package com.template.cmp.core.network.auth

import com.template.cmp.core.network.common.ApiRoute
import com.template.cmp.core.network.common.api.ApiHttpClient
import com.template.cmp.core.network.common.api.request.GetRequest
import com.template.cmp.core.network.common.api.request.PostRequest
import com.template.cmp.core.network.common.api.response.NoteApiResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.mp.KoinPlatformTools

class AuthApi(
    private val apiHttpClient: ApiHttpClient,
) {
    private val get by KoinPlatformTools.defaultContext().get().inject<GetRequest>()
    private val post by KoinPlatformTools.defaultContext().get().inject<PostRequest>()
    internal val jsonWithDefaults = Json { encodeDefaults = true }


    suspend fun login(){
//    : NoteApiResponse<Unit> {

        val loginRequest = LoginRequest("gdfg", "45654456")
        val result = get.getRequest<Unit>(
            url = ApiRoute.AUTH,
        )
//        val result = post.postRequest<Unit>(
//            url = ApiRoute.AUTH,
//            requestBody = jsonWithDefaults.encodeToString(loginRequest)
//        )
//        result.isSuccessful
//        return result
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