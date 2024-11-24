package com.themplate.cmp
import io.github.alexzhirkevich.cupertino.adaptive.Theme

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun determineTheme(): Theme = Theme.Material3