package com.eps.pakistan.network.common.api.secure

import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray
import kotlin.experimental.xor

// TODO Improve this logic later in task #38

object SafeHelper {

    private var salt: String = ""

    fun init(salt: String) {
        this.salt = salt
    }

    fun hide(string: String): ByteArray {
        val text = string.toByteArray(Charsets.UTF_8)
        val cipher = this.salt.toByteArray(Charsets.UTF_8)
        val length = cipher.size

        val encrypted = ByteArray(text.size)

        for ((index, t) in text.withIndex()) {
            encrypted[index] = t xor cipher[index % length]
        }

        return encrypted
    }

    fun reveal(key: ByteArray): String {
        val cipher = this.salt.toByteArray(Charsets.UTF_8)
        val length = cipher.size

        val decrypted = ByteArray(key.size)

        for ((index, k) in key.withIndex()) {
            decrypted[index] = k xor cipher[index % length]
        }

        return decrypted.decodeToString()
    }
}
