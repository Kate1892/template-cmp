package com.eps.pakistan.network.common.storage

import com.russhwolf.settings.get
import com.russhwolf.settings.set

private const val ACCESS_TOKEN_KEY = "access_token"
private const val REFRESH_TOKEN_KEY = "refresh_token"
private const val ACCOUNT_TOKEN_KEY = "account_token"
private const val PUBLIC_KEY_KEY = "public_key"
private const val CURRENT_PRIVATE_KEY_KEY = "current_private_key"
private const val NEW_PRIVATE_KEY_KEY = "new_private_key"
private const val OTP_SESSION_ID_KEY = "otp_session_id"
private const val PASSWORD_SESSION_ID_KEY = "password_session_id"
private const val DEVICE_TOKEN_KEY = "device_token"
private const val IS_USER_LOGGED_IN_KEY = "is_user_logged_in"
private const val USER_PIN = "u_p"

internal object NetworkStorage {

    private val encryptedSettings = NetworkStorageSettings().encryptedSettings

    var accessToken: String
        get() {
            return encryptedSettings[ACCESS_TOKEN_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[ACCESS_TOKEN_KEY] = value
        }

    var refreshToken: String
        get() {
            return encryptedSettings[REFRESH_TOKEN_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[REFRESH_TOKEN_KEY] = value
        }

    var accountToken: String
        get() {
            return encryptedSettings[ACCOUNT_TOKEN_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[ACCOUNT_TOKEN_KEY] = value
        }

    var publicKey: String
        get() {
            return encryptedSettings[PUBLIC_KEY_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[PUBLIC_KEY_KEY] = value
        }

    var currentPrivateKey: String
        get() {
            return encryptedSettings[CURRENT_PRIVATE_KEY_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[CURRENT_PRIVATE_KEY_KEY] = value
        }

    var newPrivateKey: String
        get() {
            return encryptedSettings[NEW_PRIVATE_KEY_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[NEW_PRIVATE_KEY_KEY] = value
        }

    var otpSessionId: String
        get() {
            return encryptedSettings[OTP_SESSION_ID_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[OTP_SESSION_ID_KEY] = value
        }

    var passwordSessionId: String
        get() {
            return encryptedSettings[PASSWORD_SESSION_ID_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[PASSWORD_SESSION_ID_KEY] = value
        }

    var deviceToken: String
        get() {
            return encryptedSettings[DEVICE_TOKEN_KEY] ?: ""
        }
        set(value) {
            encryptedSettings[DEVICE_TOKEN_KEY] = value
        }

    var isUserLoggedIn: Boolean
        get() {
            return encryptedSettings[IS_USER_LOGGED_IN_KEY] ?: false
        }
        set(value) {
            encryptedSettings[IS_USER_LOGGED_IN_KEY] = value
        }

    var pincode: String
        get() {
            return encryptedSettings[USER_PIN] ?: ""
        }
        set(value) {
            encryptedSettings[USER_PIN] = value
        }

    fun clearData() {
        encryptedSettings.clear()
    }
}
