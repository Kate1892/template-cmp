@file:Suppress("MaximumLineLength", "MaxLineLength")

package com.eps.pakistan.network.common.api.request

import com.eps.pakistan.network.NetworkData
import com.eps.pakistan.network.NetworkData.jsonWithDefaults
import com.eps.pakistan.network.common.REQUEST_BODY_KEY_SIGN
import com.eps.pakistan.network.common.REQUEST_BODY_KEY_TIMESTAMP
import com.eps.pakistan.network.common.api.secure.SafeHelper
import com.eps.pakistan.network.common.api.secure.getCurrentTimestamp
import com.eps.pakistan.network.common.api.secure.getNotShielding
import com.eps.pakistan.network.common.api.secure.getSignatureAuth
import com.eps.pakistan.network.common.api.secure.getSignatureNotAuth
import com.eps.pakistan.network.common.storage.NetworkStorage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

private val DEV_KEY_NOT_AUTHORIZED =
    byteArrayOf(103, 119, 41, 38, 19, 95, 15, 6, 4, 0, 93, 0, 101, 118, 114, 117, 77, 80, 13, 5, 7, 83, 90, 95, 107, 113, 120, 113, 71, 92, 8, 84)
private val PROD_KEY_NOT_AUTHORIZED =
    byteArrayOf(37, 45, 126, 42, 4, 1, 22, 1, 7, 14, 87, 83, 54, 48, 63, 43, 13, 92, 22, 19, 1, 89, 76, 9, 42, 114, 35, 119, 2, 7, 11, 12)

internal inline fun <reified T : PostRequestBody> T.signedNotAuth(): JsonElement {
    val jsonElement =
        jsonWithDefaults.encodeToJsonElement(
            jsonWithDefaults.encodeToJsonElement(this).jsonObject.toMutableMap().apply {
                put(REQUEST_BODY_KEY_TIMESTAMP, JsonPrimitive(getCurrentTimestamp()))
            }
        )
    val sign = getSignatureNotAuth(
        getKeyNotAuthorized(),
        jsonWithDefaults.encodeToString(jsonElement).getNotShielding()
    )
    val jsonWithSignMap = jsonElement.jsonObject.toMutableMap().apply {
        put(REQUEST_BODY_KEY_SIGN, JsonPrimitive(sign))
    }

    return jsonWithDefaults.encodeToJsonElement(jsonWithSignMap)
}

internal inline fun <reified T : PostRequestBody> T.signedAuth(): JsonElement {
    val jsonWithTimestamp =
        jsonWithDefaults.encodeToJsonElement(
            jsonWithDefaults.encodeToJsonElement(this).jsonObject.toMutableMap().apply {
                put(REQUEST_BODY_KEY_TIMESTAMP, JsonPrimitive(getCurrentTimestamp()))
            }
        )

    if (NetworkStorage.currentPrivateKey.isEmpty()) {
        return jsonWithDefaults.encodeToJsonElement(jsonWithTimestamp)
    }

    val sign = getSignatureAuth(
        NetworkStorage.currentPrivateKey,
        jsonWithDefaults.encodeToString(jsonWithTimestamp).getNotShielding()
    )
    val jsonWithSignMap = jsonWithTimestamp.jsonObject.toMutableMap().apply {
        put(REQUEST_BODY_KEY_SIGN, JsonPrimitive(sign))
    }

    return jsonWithDefaults.encodeToJsonElement(jsonWithSignMap)
}

internal fun getKeyNotAuthorized(): String {
    return if (NetworkData.isProduction) {
        SafeHelper.reveal(PROD_KEY_NOT_AUTHORIZED)
    } else {
        SafeHelper.reveal(DEV_KEY_NOT_AUTHORIZED)
    }
}
