package com.eps.pakistan.network.profile

import com.eps.pakistan.network.ApiRoute
import com.eps.pakistan.network.NetworkData.jsonWithDefaults
import com.eps.pakistan.network.common.api.ApiHttpClient
import com.eps.pakistan.network.common.api.request.getRequestAuth
import com.eps.pakistan.network.common.api.request.postRequestAuth
import com.eps.pakistan.network.common.api.request.postRequestAuthMultipartPhoto
import com.eps.pakistan.network.common.api.request.signedAuth
import com.eps.pakistan.network.common.api.response.EpsApiResponse
import com.eps.pakistan.network.common.api.secure.getMd5ForBytes
import com.eps.pakistan.network.profile.profile.ChecksumBody
import com.eps.pakistan.network.profile.profile.ProfileDto
import com.eps.pakistan.network.profile.profile.ProfilePhotoDto
import com.eps.pakistan.network.profile.profile.SaveProfileRequestBody
import kotlinx.serialization.encodeToString

class ProfileApi {

    private val httpClient = ApiHttpClient.client

    suspend fun getProfile(): EpsApiResponse<ProfileDto> {
        return httpClient.getRequestAuth(url = ApiRoute.PROFILE)
    }

    suspend fun updateProfileEmail(email: String): EpsApiResponse<ProfileDto> {
        val body = SaveProfileRequestBody(email).signedAuth()

        return httpClient.postRequestAuth<ProfileDto>(
            url = ApiRoute.SAVE_PROFILE,
            requestBody = jsonWithDefaults.encodeToString(body),
        )
    }

    suspend fun setProfilePhoto(bytes: ByteArray): EpsApiResponse<ProfilePhotoDto> {
        val body = ChecksumBody(checksum = getMd5ForBytes(bytes)).signedAuth()

        return httpClient.postRequestAuthMultipartPhoto<ProfilePhotoDto>(
            url = ApiRoute.SET_PROFILE_PHOTO,
            requestBody = jsonWithDefaults.encodeToString(body),
            data = bytes,
        )
    }
}
