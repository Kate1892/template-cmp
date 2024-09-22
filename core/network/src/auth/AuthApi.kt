package com.eps.pakistan.network.auth

import com.eps.pakistan.network.ApiRoute
import com.eps.pakistan.network.NetworkData
import com.eps.pakistan.network.NetworkData.jsonWithDefaults
import com.eps.pakistan.network.auth.appversion.AppVersionDto
import com.eps.pakistan.network.auth.auth.AuthDto
import com.eps.pakistan.network.auth.auth.AuthRequestBody
import com.eps.pakistan.network.auth.changepassword.ChangePasswordRequestBody
import com.eps.pakistan.network.auth.checkpassword.CheckPasswordRequestBody
import com.eps.pakistan.network.auth.confirmotp.ConfirmOtpActionDto
import com.eps.pakistan.network.auth.confirmotp.ConfirmOtpDto
import com.eps.pakistan.network.auth.confirmotp.ConfirmOtpRequestBody
import com.eps.pakistan.network.auth.login.LoginDto
import com.eps.pakistan.network.auth.login.LoginRequestBody
import com.eps.pakistan.network.auth.repeatotp.RepeatOtpRequestBody
import com.eps.pakistan.network.auth.signup.SignupRequestBody
import com.eps.pakistan.network.auth.status.AuthStatusDto
import com.eps.pakistan.network.auth.status.AuthStatusRequestBody
import com.eps.pakistan.network.common.api.ApiHttpClient
import com.eps.pakistan.network.common.api.request.getRequestNotSigned
import com.eps.pakistan.network.common.api.request.postRequestAuth
import com.eps.pakistan.network.common.api.request.postRequestNotAuth
import com.eps.pakistan.network.common.api.request.signedAuth
import com.eps.pakistan.network.common.api.request.signedNotAuth
import com.eps.pakistan.network.common.api.response.ApiResponse
import com.eps.pakistan.network.common.api.response.EpsApiResponse
import com.eps.pakistan.network.common.api.response.EpsApiResponseNotSigned
import com.eps.pakistan.network.common.api.secure.generatePublicPrivateKeysPair
import com.eps.pakistan.network.common.storage.NetworkStorage
import kotlinx.serialization.encodeToString

private const val OS_VERSION_QUERY_PARAM = "os"

class AuthApi {

    private val httpClient = ApiHttpClient.client

    suspend fun auth(
        phone: String,
        captchaToken: String,
        fcmToken: String,
    ): EpsApiResponse<AuthDto> {
        val keysPair = generatePublicPrivateKeysPair()
        NetworkStorage.newPrivateKey = keysPair.privateKey

        val body = AuthRequestBody(
            phone = phone,
            captchaToken = captchaToken,
            deviceCode = NetworkData.deviceCode,
            deviceName = NetworkData.deviceName,
            deviceType = NetworkData.deviceType,
            publicKey = keysPair.publicKey,
            fcmToken = fcmToken,
        ).signedNotAuth()

        val result = httpClient.postRequestNotAuth<AuthDto>(
            url = ApiRoute.AUTH,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.otpSessionId = result.body.data.sessionId
        }

        return result
    }

    suspend fun appVersion(operationSystem: String): EpsApiResponseNotSigned<AppVersionDto> {
        return httpClient.getRequestNotSigned(
            url = ApiRoute.OS_VERSION,
            queryParams = mapOf(
                OS_VERSION_QUERY_PARAM to operationSystem
            )
        )
    }

    suspend fun status(): EpsApiResponse<AuthStatusDto> {
        val body = AuthStatusRequestBody(
            deviceToken = NetworkStorage.deviceToken,
        ).signedNotAuth()

        val result = httpClient.postRequestNotAuth<AuthStatusDto>(
            url = ApiRoute.STATUS,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.passwordSessionId = result.body.data.passwordSessionId.orEmpty()
        }

        return result
    }

    suspend fun signup(
        phone: String,
        firstName: String,
        lastName: String,
        email: String,
        captchaToken: String,
        fcmToken: String,
    ): EpsApiResponse<AuthDto> {
        val keysPair = generatePublicPrivateKeysPair()
        NetworkStorage.newPrivateKey = keysPair.privateKey

        val body = SignupRequestBody(
            phone = phone,
            firstName = firstName,
            lastName = lastName,
            email = email,
            deviceCode = NetworkData.deviceCode,
            deviceName = NetworkData.deviceName,
            deviceType = NetworkData.deviceType,
            publicKey = keysPair.publicKey,
            fcmToken = fcmToken,
            captchaToken = captchaToken,
        ).signedNotAuth()

        val result = httpClient.postRequestNotAuth<AuthDto>(
            url = ApiRoute.AUTH,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.otpSessionId = result.body.data.sessionId
        }

        return result
    }

    suspend fun confirmOtp(
        code: String,
    ): EpsApiResponse<ConfirmOtpDto> {
        val body = ConfirmOtpRequestBody(
            sessionId = NetworkStorage.otpSessionId,
            code = code,
        ).signedNotAuth()

        val result = httpClient.postRequestNotAuth<ConfirmOtpDto>(
            url = ApiRoute.CONFIRM_OTP,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.deviceToken = result.body.data.deviceToken
            NetworkStorage.publicKey = result.body.data.publicKey
            NetworkStorage.currentPrivateKey = NetworkStorage.newPrivateKey

            when (result.body.data.action) {
                ConfirmOtpActionDto.Login -> {
                    NetworkStorage.isUserLoggedIn = true
                    NetworkStorage.passwordSessionId = ""
                }
                ConfirmOtpActionDto.CreatePassword -> {
                    NetworkStorage.passwordSessionId = result.body.data.passwordSessionId.orEmpty()
                }
            }
        }

        return result
    }

    suspend fun repeatOtp(): EpsApiResponse<AuthDto> {
        val body = RepeatOtpRequestBody(
            sessionId = NetworkStorage.otpSessionId,
        ).signedNotAuth()

        val result = httpClient.postRequestNotAuth<AuthDto>(
            url = ApiRoute.REPEAT_OTP,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.otpSessionId = result.body.data.sessionId
        }

        return result
    }

    suspend fun login(
        password: String,
    ): EpsApiResponse<LoginDto> {
        val body = LoginRequestBody(
            deviceToken = NetworkStorage.deviceToken,
            password = password,
        ).signedNotAuth()

        val result = httpClient.postRequestAuth<LoginDto>(
            url = ApiRoute.LOGIN,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.isUserLoggedIn = true
            NetworkStorage.accessToken = result.body.data.accessToken
            NetworkStorage.accountToken = result.body.data.accountToken
            NetworkStorage.pincode = password
            NetworkStorage.passwordSessionId = ""
        }

        return result
    }

    suspend fun checkPassword(
        oldPassword: String,
    ): EpsApiResponse<AuthDto> {
        val body = CheckPasswordRequestBody(
            deviceToken = NetworkStorage.deviceToken,
            passwordOld = oldPassword,
        ).signedAuth()

        val result = httpClient.postRequestAuth<AuthDto>(
            url = ApiRoute.CHECK_PASSWORD,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.passwordSessionId = result.body.data.sessionId
        }

        return result
    }

    suspend fun createPassword(
        password: String,
        passwordRepeat: String,
    ): EpsApiResponse<LoginDto> {
        val body = ChangePasswordRequestBody(
            sessionId = NetworkStorage.passwordSessionId,
            password = password,
            passwordRepeat = passwordRepeat,
        ).signedNotAuth()

        val result = httpClient.postRequestAuth<LoginDto>(
            url = ApiRoute.CREATE_PASSWORD,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.isUserLoggedIn = true
            NetworkStorage.accessToken = result.body.data.accessToken
            NetworkStorage.accountToken = result.body.data.accountToken
            NetworkStorage.pincode = password
            NetworkStorage.passwordSessionId = ""
        }

        return result
    }

    suspend fun changePassword(
        password: String,
        passwordRepeat: String,
    ): EpsApiResponse<LoginDto> {
        val body = ChangePasswordRequestBody(
            sessionId = NetworkStorage.passwordSessionId,
            password = password,
            passwordRepeat = passwordRepeat,
        ).signedAuth()

        val result = httpClient.postRequestAuth<LoginDto>(
            url = ApiRoute.CHANGE_PASSWORD,
            requestBody = jsonWithDefaults.encodeToString(body),
        )

        if (result is ApiResponse.Success) {
            NetworkStorage.isUserLoggedIn = true
            NetworkStorage.accessToken = result.body.data.accessToken
            NetworkStorage.accountToken = result.body.data.accountToken
            NetworkStorage.pincode = password
            NetworkStorage.passwordSessionId = ""
        }

        return result
    }
}
