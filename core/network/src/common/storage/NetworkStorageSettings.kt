@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.eps.pakistan.network.common.storage

import com.russhwolf.settings.Settings

internal expect class NetworkStorageSettings() {
    internal val encryptedSettings: Settings
}
