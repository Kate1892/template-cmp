package com.eps.pakistan.network.common.api.secure

import com.eps.pakistan.network.common.REQUEST_BODY_KEY_SIGN
import com.eps.pakistan.network.common.api.request.getKeyNotAuthorized
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

private val hexDigitsLower =
    charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')

internal expect fun generatePublicPrivateKeysPair(): GeneratedKeys

internal expect fun getSignatureNotAuth(key: String, data: String): String

internal expect fun getSignatureAuth(privateKey: String, data: String): String

internal expect fun checkServerSignAuth(responseBody: String): Boolean

internal expect fun getMd5ForBytes(bytes: ByteArray): String

internal fun getCurrentTimestamp() = Clock.System.now().epochSeconds

internal fun checkServerSignNotAuth(responseBody: String): Boolean {
    val jsonObject = Json.parseToJsonElement(responseBody).jsonObject
    val signature = getSignatureNotAuth(
        key = getKeyNotAuthorized(),
        data = jsonObject.toString().getNotShielding().getWithoutSign(),
    )

    return signature == jsonObject[REQUEST_BODY_KEY_SIGN]!!.jsonPrimitive.content
}

@Suppress("MagicNumber")
internal fun encodeHex(arr: ByteArray): CharArray {
    val cArr = CharArray(arr.size * 2)
    var i = 0
    for (b in arr) {
        val i2 = i + 1
        cArr[i] = hexDigitsLower[(b.toInt() and 0xF0) ushr 4]
        i += 2
        cArr[i2] = hexDigitsLower[b.toInt() and 0x0F]
    }
    return cArr
}

internal fun String.getNotShielding(): String {
    return this.replace("\\\\/".toRegex(), "/")
}

internal fun String.getNoLineFeeds(): String {
    return this.replace("\n".toRegex(), "")
}

internal fun String.getWithoutSign(): String {
    return this.replaceFirst(",\"sign\":\"(.+?)\"".toRegex(), "")
}

class GeneratedKeys(
    val publicKey: String,
    val privateKey: String,
)
