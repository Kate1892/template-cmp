package com.template.cmp.network.features.auth

import com.template.cmp.core.network.auth.AuthApi
import com.template.cmp.core.network.common.remoteresult.RemoteResult

class AuthRepository(
    private val authApi: AuthApi,
) {

    suspend fun auth(){
//    : RemoteResult<Unit> {
        val authResult = authApi.login()
//        return RemoteResult.Success<Unit>(Unit)
    }
}
